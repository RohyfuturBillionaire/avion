package DAO;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import annotations.BaseObject;
import annotations.Column;
import annotations.PrimaryKey;

public  class DB {
    
    public String getTableName() {
        Class<?> clazz = this.getClass();
        if (clazz.isAnnotationPresent(annotations.Table.class)) {
            return clazz.getAnnotation(annotations.Table.class).tableName();
            
        }

        return this.getClass().getSimpleName();
    }
    
    public void inserer(Connection conn) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String tableName = getTableName();
            Field[] fields = this.getClass().getDeclaredFields();
    
            StringBuilder colonne = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();
    
            int index = 0;  // Compteur pour vérifier la première colonne
            for (Field field : fields) {
                if (!field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.isAnnotationPresent(Column.class)) {
                        colonne.append(field.getAnnotation(Column.class).name()).append(",");
                        placeholders.append("?,"); 
                    }else if (field.isAnnotationPresent(BaseObject.class)) {
                        colonne.append(field.getAnnotation(BaseObject.class).idBaseName()).append(",");
                        placeholders.append("?,");
                    }
                    else{
                        colonne.append(field.getName()).append(",");
                    placeholders.append("?,");
                    }
                }
                index++;
            }
    
            String query = String.format("INSERT INTO %s (%s) VALUES (%s)",
                    tableName,
                    colonne.substring(0, colonne.length() - 1), // Retirer la dernière virgule
                    placeholders.substring(0, placeholders.length() - 1));
    
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                int paramIndex = 1;
                index = 0;  // Réinitialiser l'index pour la deuxième boucle
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (!field.isAnnotationPresent(PrimaryKey.class)) {    
                         if (field.isAnnotationPresent(BaseObject.class)) {
                            System.out.println(field.getType());
                            pstmt.setObject(paramIndex++, field.getType().getDeclaredMethod("getId").invoke(field.get(this)));
                        }  else {
                            pstmt.setObject(paramIndex++, field.get(this));
                        }
                       
                    }
                    index++;
                }
    
                pstmt.executeUpdate();
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("Erreur lors de l'insertion.");
        }
    }
    
   public List<Object> selectAll(Connection conn) throws SQLException {
    List<Object> results = new ArrayList<>();
    try {
        String tableName = getTableName();
        String query = String.format("SELECT * FROM %s", tableName);

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                DB obj = this.getClass().getDeclaredConstructor().newInstance();
                Field[] fields = this.getClass().getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value;
                    if (field.isAnnotationPresent(Column.class)) {
                        value = rs.getObject(field.getAnnotation(Column.class).name());
                    } else if (field.isAnnotationPresent(BaseObject.class)) {
                        value = ((DB) field.getType().getDeclaredConstructor().newInstance()).getById(conn, rs.getInt(field.getAnnotation(BaseObject.class).idBaseName()));
                    } else {
                        value = rs.getObject(field.getName());
                    }

                    // condition si le type est BigDecimal
                    if (value != null) {
                        if (field.getType() == double.class && value instanceof BigDecimal) {
                            value = ((BigDecimal) value).doubleValue();
                        } else if (field.getType() == int.class && value instanceof Integer) {
                            value = ((Integer) value).intValue();
                        } else if (field.getType() == short.class && value instanceof Integer) {
                            value = ((Integer) value).shortValue();
                        }
                    }

                    field.set(obj, value);
                }

                results.add(obj);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new SQLException("Erreur lors de la récupération des données.", e);
    }
    return results;
}

    public void deleteById(Connection conn, int id) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String tableName = getTableName();
            Field[] fields = this.getClass().getDeclaredFields();
    
            // Recherche du champ correspondant à l'ID
            String primaryKeyField = null;
            for (Field field : fields) {
                if (field.getName().toLowerCase().contains("id")) {
                    primaryKeyField = field.getName();
                    break;
                }
            }
    
            if (primaryKeyField == null) {
                throw new SQLException("Aucune colonne contenant 'id' trouvée dans la classe.");
            }
    
            String query = String.format("DELETE FROM %s WHERE %s = ?", tableName, primaryKeyField);
    
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id); // Assigne l'ID passé en argument
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " ligne(s) supprimée(s).");
            }
            
            conn.commit(); // Valide la transaction
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback(); // Annule la transaction en cas d'erreur
            System.out.println("Erreur lors de la suppression par ID.");
        }
    }
    public void updateById(Connection conn, int id) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String tableName = getTableName();
            Field[] fields = this.getClass().getDeclaredFields();
    
            // Construction de la clause SET
            StringBuilder setClause = new StringBuilder();
            for (int i = 1; i < fields.length; i++) { // Commencer à 1 pour ignorer l'ID (1ère colonne)
                setClause.append(fields[i].getName()).append(" = ?, ");
            }
    
            // Supprimer la dernière virgule et espace
            String setClauseStr = setClause.substring(0, setClause.length() - 2);
    
            String query = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, setClauseStr, fields[0].getName());
    
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                int parameterIndex = 1;
    
                // Ajout des valeurs pour les colonnes sauf l'ID
                for (int i = 1; i < fields.length; i++) { // Commencer à 1 pour ignorer l'ID
                    fields[i].setAccessible(true);
                    pstmt.setObject(parameterIndex++, fields[i].get(this));
                }
    
                // Ajout de l'ID comme dernier paramètre
                pstmt.setInt(parameterIndex, id);
    
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
            }
    
            conn.commit(); // Valide la transaction
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback(); // Annule la transaction en cas d'erreur
            System.out.println("Erreur lors de la mise à jour par ID.");
        }
    }
    public Object getById(Connection conn, int id) throws SQLException {
        try {
            String tableName = getTableName();
            Field[] fields = this.getClass().getDeclaredFields();
            
            // Find ID field name
            String idField = fields[0].getName();
            if (fields[0].isAnnotationPresent(Column.class)) {
                idField = fields[0].getAnnotation(Column.class).name();
            }
            
            String query = String.format("SELECT * FROM %s WHERE %s = ?", tableName, idField);
    
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        Object obj = this.getClass().getDeclaredConstructor().newInstance();
                        
                        for (Field field : fields) {
                            field.setAccessible(true);
                            Object value;
                            if (field.isAnnotationPresent(Column.class)) {
                                value = rs.getObject(field.getAnnotation(Column.class).name());
                            } else if (field.isAnnotationPresent(BaseObject.class)) {
                                value = ((DB) field.getType().getDeclaredConstructor().newInstance()).getById(conn, rs.getInt(field.getAnnotation(BaseObject.class).idBaseName()));
                            } else {
                                value = rs.getObject(field.getName());
                            }
    
                            // condition si le type est BigDecimal
                            if (value != null) {
                                if (field.getType() == double.class && value instanceof BigDecimal) {
                                    value = ((BigDecimal) value).doubleValue();
                                } else if (field.getType() == int.class && value instanceof Integer) {
                                    value = ((Integer) value).intValue();
                                } else if (field.getType() == short.class && value instanceof Integer) {
                                    value = ((Integer) value).shortValue();
                                }
                            }
    
                            field.set(obj, value);
                        }
                        
                        return obj;
                    }
                }
            }
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération par ID.");
        }
        return null;
    }
    
    
    
}