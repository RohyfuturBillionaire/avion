<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="./bootstrap-5.3.2-dist/css/bootstrap.min.css">
</head>
<body>

    <h1>inscription</h1>
    <div class="container">
        <form action="inscription" method="POST" role="form">
            <legend>inscription form</legend>
        
            <div class="form-group">
                <label for="username">username</label>
                <input type="text" name="user.nomUtilisateur" placeholder="username">
            </div>
        
            <div class="form-group">
                <label for="">password</label>
                <input type="password" name="user.pwdUtilisateur" placeholder="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        
    </div>
   
    
</body>
</html>