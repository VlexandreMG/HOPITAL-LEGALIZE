<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<% 
Vector<MedOrdonnance> ordonnancesMere = new Vector<>();
String idConsultation = request.getParameter("idConsultation");
session.setAttribute("idConsultation", idConsultation);
ordonnancesMere = MedOrdonnanceDAO.getOrdonnanceByIdCLient(idConsultation);
Client iencli= ClientDAO.getClientByIdConsultation(idConsultation);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordonnance</title>
</head>
<body>
    <%= idConsultation %>
    <h2>Ordonnance pour le patient : <%= iencli.getNom() %> </h2>

    <%-- <h2>Test des ordonnances</h2>
<p>ID Consultation: <%= idConsultation %></p>
<p>Nombre d'ordonnances: <%= ordonnancesMere.size() %></p>

<% for (MedOrdonnance ord : ordonnancesMere) { %>
    <div style="border: 1px solid #ccc; margin: 10px; padding: 10px;">
        <p>ID: <%= ord.getId() %></p>
        <p>Médecin: <%= ord.getIdMedecin() %></p>
        <p>Jours: <%= ord.getNbJours() %></p>
        <p>Observation: <%= ord.getObservation() %></p>
    </div>
<% } %> --%>

    <h2>Ordonnance medicale : </h2>

    <form action="" method="post">
        <p>Date : <input type="date" name="dateOrdonnance"></p>
        <p>Duree : <input type="number" name="duree"></p>

        <hr width="100%" size="2" color="black">

        <h3>Liste des Medicaments</h3>

        <div id="forms">
            <div class="med-card" id="medicament-template">
                <div class="med-header">
                    <span class="med-number">Médicament #1</span>
                </div>

                <div class="grid-2">
                    <div class="group">
                        <label>Médicament :</label>
                        <select name="idmedicament[]" required>
                            <option value="">-- Selectionner --</option>
                            <%
                                Vector<String> medicaments = MedOrdonnanceDAO.getMedicaments();
                                for (String medicament : medicaments) {
                            %>
                                <option value="<%= medicament %>"><%= medicament %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="group">
                        <label>Posologie :</p>
                        <input type="text" name="posologie[]" placeholder="Ex: 1 cp matin et soir">
                    </div>
                </div>

                <div class="grid-4">
                    <div class="group">
                        <p>Quantite :</p>
                        <input type="number" name="quantite[]" min="1" required>
                    </div>

                    <div class="group">
                        <p>Unite:</p>
                        <input type="text" name="unite[]" placeholder="boîte, cp">
                    </div>

                    <div class="group">
                        <p>Duree (jours) :</p>
                        <input type="number" name="nb_jours_med[]" min="1">
                    </div>

                    <div class="group">
                        <p>Prise en charge (%) :</p>
                        <input type="number" name="taux[]" min="0" max="100">
                    </div>
                </div>

                <div class="group">
                    <p>Remarque :</p>
                    <input type="text" name="remarque[]" placeholder="Remarque spécifique...">
                </div>
            </div>
        </div>

        <p><button type="button" class="add-btn" onclick="addForm()">+ Ajouter un médicament</button></p>
        <input type="submit" class="submit-btn" value="Valider l'ordonnance">

    </form>
</div>

<script>
let medCount = 1;
let medicamentTemplate = '';

// Initialiser le template au chargement de la page
document.addEventListener('DOMContentLoaded', function() {
    const templateElement = document.getElementById('medicament-template');
    if (templateElement) {
        // Cloner l'élément et le stocker comme HTML
        const clone = templateElement.cloneNode(true);
        clone.removeAttribute('id'); // Retirer l'id du clone
        medicamentTemplate = clone.outerHTML;
        
        console.log('Template chargé avec succès');
    }
});

function addForm() {
    medCount++;
    const formsDiv = document.getElementById('forms');
    
    // Créer un nouvel élément à partir du template
    const newForm = document.createElement('div');
    newForm.innerHTML = medicamentTemplate;
    newForm.className = 'med-card';
    
    // Mettre à jour le numéro du médicament
    const medNumber = newForm.querySelector('.med-number');
    if (medNumber) {
        medNumber.textContent = `Médicament #${medCount}`;
    }
    
    // Ajouter le bouton supprimer
    const medHeader = newForm.querySelector('.med-header');
    if (medHeader && !medHeader.querySelector('.remove-btn')) {
        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.className = 'remove-btn';
        removeBtn.textContent = 'Supprimer';
        removeBtn.onclick = function() { removeForm(this); };
        medHeader.appendChild(removeBtn);
    }
    
    // Utiliser appendChild pour ajouter au DOM
    formsDiv.appendChild(newForm);
    formsDiv.scrollTop = formsDiv.scrollHeight;
}

function removeForm(button) {
    if (document.querySelectorAll('.med-card').length > 1) {
        button.closest('.med-card').remove();
        updateMedNumbers();
    }
}

function updateMedNumbers() {
    const cards = document.querySelectorAll('.med-card');
    medCount = cards.length;
    cards.forEach((card, index) => {
        const numberSpan = card.querySelector('.med-number');
        if (numberSpan) {
            numberSpan.textContent = `Médicament #${index + 1}`;
        }
    });
}

// Gestionnaire de soumission
document.getElementById('mainForm').addEventListener('submit', function(e) {
    alert("Ordonnance validée avec succès !");
});
</script>



<a href="accueil.jsp">Retour</a>
</body>
</html>