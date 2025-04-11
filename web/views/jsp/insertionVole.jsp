<%@ include file="header.jsp" %>
<%@ page import="model.*" %>
<%
    List<Object> avions = (List<Object>) request.getAttribute("Lavions");
    List<Object> villes = (List<Object>) request.getAttribute("Lvilles");
    List<Object> vols = (List<Object>) request.getAttribute("Lvols");
%>
    <div class="container">
       <form action="insertvol" method="POST" class="form-inline" role="form">
    
       <div class="form-group">
        <label for="inputtypeAvion">avion</label>
        <select id="inputtypeAvion" class="form-control" name="avion" >
           <% for (Object avion : avions) { %>
                <option value="<%= ((Avion)avion).getId() %>"><%= ((Avion)avion).getModelAvion() %></option>
            <% } %>
        </select>
       </div>
         
        <div class="form-group">
             <label for="dateDepart">depart</label>
            <input type="datetime-local" class="form-control" id="dateDepart" name="dtDebut">
        </div>

        <div class="form-group">
            <label for="dateArrive">arrive</label>
            <input type="datetime-local" class="form-control" id="dateArrive" name="dtFin">
        </div>
        
        <div class="form-group">
            <label for="VilleDepart">ville de depart</label>
            <select  id="VilleDepart" class="form-control" name="villeDepart">
                <% for (Object ville : villes) { %>
                    <option value="<%= ((Ville)ville).getId() %>"><%= ((Ville)ville).getNomVille() %></option>
                <% } %>
            </select>
        </div>
        
        <div class="form-group">
            <label for="VilleArrive">ville d'arrive</label>
            <select id="VilleArrive" class="form-control" name="villeArrive" >
                <% for (Object ville : villes) { %>
                    <option value="<%= ((Ville)ville).getId() %>"><%= ((Ville)ville).getNomVille() %></option>
                <% } %>
            </select>
        </div>

        
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
        
    </div>
<%@ include file="footer.jsp" %>