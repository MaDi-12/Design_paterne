<?php
// Récupération des valeurs 
$username = $_POST['username'];
$password = $_POST['password'];

// Vérification des valeurs par rapport aux données réelles
if ($username === 'madi' && $password === 'passer') {
    // Authentification réussie
    echo 'Authentification réussie !';
} else {
    // Authentification échouée
    echo 'Nom d\'utilisateur ou mot de passe incorrect.';
}
?>
