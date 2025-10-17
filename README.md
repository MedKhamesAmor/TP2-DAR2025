# TP2 - Activité 2-1: Client-Serveur Calculator avec Sockets TCP

## Description
Implémentation d'un client et serveur en Java utilisant les sockets TCP pour échanger des opérations mathématiques complètes.

## Fonctionnalités
- Échange de chaînes de caractères entre client et serveur
- Support des opérations: +, -, *, /
- Validation des entrées côté client
- Gestion d'erreurs (division par zéro, format invalide)
- Calcul côté serveur et retour du résultat

## Structure du projet
src/
├── serverPackage/
│ └── Server.java
└── clientPackage/
└── Client.java

## Comment exécuter
1. Lancer Server.java en premier
2. Lancer Client.java ensuite
3. Entrer des opérations au format: `nombre opérateur nombre`
4. Exemples: `5 + 3`, `10 * 2`, `8 / 4`

## Auteur
Med Khames Amor - LSI3 - Développement d'Applications Réparties
