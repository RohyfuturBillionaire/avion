<%@ include file="header.jsp" %>
<%@ page import="model.*" %>
<%
    List<Object> avions = (List<Object>) request.getAttribute("Lavions");
    List<Object> villes = (List<Object>) request.getAttribute("Lvilles");

%>

<div class="page-header">
  <h1><small>Recherche de vole</small></h1>
  <div class="container">
    
    <form action="" method="POST" class="form-inline" role="form">
    
       <div class="form-group">
        <label for="inputtypeAvion">avion</label>
        <select name="avion" id="inputtypeAvion" class="form-control" >
           <% for (Object avion : avions) { %>
                <option value="<%= ((Avion)avion).getId() %>"><%= ((Avion)avion).getModelAvion() %></option>
            <% } %>
        </select>
       </div>
         
        <div class="form-group">
             <label for="dateDepart">depart</label>
            <input type="datetime-local" class="form-control" id="dateDepart">
        </div>

        <div class="form-group">
            <label for="dateArrive">arrive</label>
            <input type="datetime-local" class="form-control" id="dateArrive">
        </div>
        
        <div class="form-group">
            <label for="VilleDepart">ville de depart</label>
            <select name="ville" id="VilleDepart" class="form-control" >
                <% for (Object ville : villes) { %>
                    <option value="<%= ((Ville)ville).getId() %>"><%= ((Ville)ville).getNomVille() %></option>
                <% } %>
            </select>
        </div>
        
        <div class="form-group">
            <label for="VilleArrive">ville d'arrive</label>
            <select name="ville" id="VilleArrive" class="form-control" >
                <% for (Object ville : villes) { %>
                    <option value="<%= ((Ville)ville).getId() %>"><%= ((Ville)ville).getNomVille() %></option>
                <% } %>
            </select>
        </div>

        
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    
    
</div>
</div>

<div class="container">
  <h2>list des vole
    
  </h2>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>numero du vole</th>
        <th>avion</th>
        <th>date depart</th>
        <th>date arrive</th>
        <th>ville depart</th>
        <th>ville arrive</th>
        <th>reservation</th>
       
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>
            air bus
        </td>
        <td>
            2022-10-10 10:00
        </td>
        <td>
            2022-10-10 12:00
        </td>
        <td>
            paris
        </td>
        <td>
            londre
        </td>
        <td>
            <a href="#">reserver</a>
        </td>
      </tr>

      <tr>
        <td>1</td>
        <td>
            air bus
        </td>
        <td>
            2022-10-10 10:00
        </td>
        <td>
            2022-10-10 12:00
        </td>
        <td>
            paris
        </td>
        <td>
            londre
        </td>
        <td>
            <a href="#">reserver</a>
        </td>
      </tr>
      
      <!-- Add more rows as needed -->
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>