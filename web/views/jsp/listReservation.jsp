<%@ include file="header.jsp" %>
<%@ page import="model.*" %>
<%
    List<Object> reservations = (List<Object>) request.getAttribute("reservations");
%>
<div class="container">
  <h2>list des reservation
    
  </h2>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>numero du reservation</th>
        <th>Date reservation</th>
        <th>duree</th>
        <th>itineraire</th>
        <th> export </th>
        <th> cancel </th>
      </tr>
    </thead>
    <tbody>
        <% for (Object reservation : reservations) { %>
            <tr>
                <td><%= ((Reservation)reservation).getId() %></td>
                <td><%= ((Reservation)reservation).getDateReservation() %></td>
                <td><%= ((Reservation)reservation).getVol().getDtDebut()+"-"+((Reservation)reservation).getVol().getDtFin() %></td>
                <td><%= ((Reservation)reservation).getVol().getVilleDepart().getNomVille()+"-"+((Reservation)reservation).getVol().getVilleArrivee().getNomVille() %></td>            
                <td> 
                    <form action="exportReservation" method="POST" class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <input type="hidden" class="form-control" name="id" value="<%= ((Reservation)reservation).getId() %>">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-success">reserver</button>
                    </form>
                </td>
                <td> <button type="submit" class="btn btn-danger">cancel</button></td>
            </tr>
        <% } %> 
      <!-- Add more rows as needed -->
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>