#!/bin/bash
set -e  # Stopper le script si une commande échoue

# ==============================
# Configuration du projet
# ==============================
APP_NAME="Hopital-legalize"
SRC_DIR="src/java"
WEB_DIR="src/webapp"
#XML_DIR="src/main/xml"
BUILD_DIR="build"
LIB_DIR="lib"
TOMCAT_WEBAPPS="/home/balou/balouJava/apache-tomcat-10.1.43/webapps"
SERVLET_API_JAR="/home/balou/balouJava/apache-tomcat-10.1.43/lib/servlet-api.jar"

# ==============================
# Préparation du répertoire de build
# ==============================
echo "🧹 Nettoyage de l'ancien build..."
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR/WEB-INF/classes"

# ==============================
# Compilation du code Java
# ==============================
echo "🔧 Compilation des fichiers Java..."
find "$SRC_DIR" -name "*.java" > sources.txt

# Compilation (packages ou pas, javac gère la structure automatiquement)
javac -cp "$SERVLET_API_JAR" -d "$BUILD_DIR/WEB-INF/classes" @sources.txt
rm sources.txt

# ==============================
# Copie des fichiers Web
# ==============================
echo "📂 Copie des fichiers Web..."
cp -r "$WEB_DIR"/* "$BUILD_DIR"

# Copie du XML (s'il existe)
if [ -d "$XML_DIR" ]; then
    cp -r "$XML_DIR"/* "$BUILD_DIR/WEB-INF/"
fi

# ==============================
# Gestion des dépendances
# ==============================
echo "📦 Gestion des dépendances..."
mkdir -p "$BUILD_DIR/WEB-INF/lib"

# Copier le driver Oracle JDBC s’il existe
if [ -f "$WEB_DIR/WEB-INF/lib/ojdbc11.jar" ]; then
    cp "$WEB_DIR/WEB-INF/lib/ojdbc11.jar" "$BUILD_DIR/WEB-INF/lib/"
fi

# Copier d’autres libs locales
if [ -d "$LIB_DIR" ]; then
    cp -r "$LIB_DIR"/* "$BUILD_DIR/WEB-INF/lib/" 2>/dev/null || true
fi

# ==============================
# Création du WAR
# ==============================
echo "📦 Génération du fichier WAR..."
cd "$BUILD_DIR"
jar -cvf "$APP_NAME.war" *
cd - >/dev/null

# ==============================
# Déploiement sur Tomcat
# ==============================
echo "🚀 Déploiement sur Tomcat..."
cp -f "$BUILD_DIR/$APP_NAME.war" "$TOMCAT_WEBAPPS/"

echo "✅ Déploiement de $APP_NAME terminé avec succès !"

