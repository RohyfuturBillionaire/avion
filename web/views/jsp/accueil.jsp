<%@ include file="header.jsp" %>
<%@ page import="model.*" %>
<%
    List<Object> avions = (List<Object>) request.getAttribute("Lavions");
    List<Object> villes = (List<Object>) request.getAttribute("Lvilles");
    List<Object> vols = (List<Object>) request.getAttribute("Lvols");
%>

<div class="page-header">
  <h1><small>Recherche de vole</small></h1>
  <div class="container">
    
    <form action="listWcritaire" method="POST" class="form-inline" role="form">
    
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
            <input type="datetime-local" class="form-control" id="dateDepart" name="dateDepart">
        </div>

        <div class="form-group">
            <label for="dateArrive">arrive</label>
            <input type="datetime-local" class="form-control" id="dateArrive" name="dateArrive">
        </div>
        
        <div class="form-group">
            <label for="VilleDepart">ville de depart</label>
            <select name="villeDepart" id="VilleDepart" class="form-control" >
                <% for (Object ville : villes) { %>
                    <option value="<%= ((Ville)ville).getId() %>"><%= ((Ville)ville).getNomVille() %></option>
                <% } %>
            </select>
        </div>
        
        <div class="form-group">
            <label for="VilleArrive">ville d'arrive</label>
            <select name="villeArrive" id="VilleArrive" class="form-control" >
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
        <th>numero du vol</th>
        <th>avion</th>
        <th>date depart</th>
        <th>date arrive</th>
        <th>ville depart</th>
        <th>ville arrive</th>
        <th>reservation</th>
        <th>update</th>
        <th>delete</th>
       
      </tr>
    </thead>
    <tbody>

        <% for (Object vol : vols) { %>
            <tr>
                <td><%= ((Vol)vol).getId() %></td>
                <td><%= ((Vol)vol).getAvion().getModelAvion() %></td>
                <td><%= ((Vol)vol).getDtDebut() %></td>
                <td><%= ((Vol)vol).getDtFin() %></td>
                <td><%= ((Vol)vol).getVilleDepart().getNomVille() %></td>
                <td><%= ((Vol)vol).getVilleArrivee().getNomVille() %></td>
                <td><a href="#">reserver</a></td>
                <td>
                    <form action="updateVol" method="POST" class="form-inline" role="form">
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="idVol" name="idVol" placeholder="Input field" value="<%= ((Vol)vol).getId() %>">
                            <input type="hidden" class="form-control" id="idAvion" name="idAvion" placeholder="Input field" value="<%= ((Vol)vol).getAvion().getId() %>">
                            <input type="hidden" class="form-control" id="dtDebut" name="dtDebut" placeholder="Input field" value="<%= ((Vol)vol).getDtDebut().toLocalDateTime() %>">
                            <input type="hidden" class="form-control" id="dtFin" name="dtFin" placeholder="Input field" value="<%= ((Vol)vol).getDtFin().toLocalDateTime() %>">
                            <input type="hidden" class="form-control" id="idVilleArrivee" name="idVilleArrivee" placeholder="Input field" value="<%= ((Vol)vol).getVilleArrivee().getId() %>">
                            <input type="hidden" class="form-control" id="idVilleDepart" name="idVilleDepart" placeholder="Input field" value="<%= ((Vol)vol).getVilleDepart().getId() %>">
                            
                        </div>
                        <button type="submit" class="btn btn-primary">update</button>
                    </form>
                </td> 
                <td>                
                 <form action="deleteVol" method="POST" class="form-inline" role="form">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="idVol" name="idVol" placeholder="Input field" value="<%= ((Vol)vol).getId() %>">
                    </div>
                    
                    <button type="submit" class="btn btn-danger">delete</button>
                    
                  </form>
                </td>
            </tr>
        <% } %>
        


      
      
      <!-- Add more rows as needed -->
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>