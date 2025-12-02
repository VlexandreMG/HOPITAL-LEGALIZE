<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h3>Veuillez ajouter la nouvelle consultation : </h3>
    <form action="addConsultation" method="post">
        <p>Date : <input type="date" name="daty"></p>
        <p>Description : <input type="text" name="description"></p>
        <p>Heure arrivee : <input type="datetime-local" name="heure_arrivee"></p>
        <p>Heure de depart : <input type="datetime-local" name="heure_depart"></p>
        <input type="submit" value="Créer la consultation">
    </form>
</body>
</html>