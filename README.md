# BudgetFlow

BudgetFlow est une application Android de gestion de dépenses, conçue pour aider les utilisateurs à suivre et organiser leurs transactions quotidiennes de manière simple et efficace.

## 📱 Fonctionnalités Principales

- **Ajouter des Dépenses** : Saisissez le montant, la catégorie et la date.
- **Modifier des Dépenses** : Mettez à jour les informations des dépenses existantes.
- **Supprimer des Dépenses** : Gérez votre historique en supprimant les transactions obsolètes.
- **Afficher la Liste des Dépenses** : Consultez toutes vos dépenses sous forme de liste détaillée.
- **Voir le Détail d'une Dépense** : Affichage complet d'une transaction avec possibilité de suppression.
- **Notifications de Rappel** : Recevez des rappels automatiques pour ajouter vos dépenses.

## 🛠️ Technologies Utilisées

- **Room Database** : Gestion locale des données.
- **RecyclerView** : Affichage dynamique et optimisé des listes.
- **BroadcastReceiver** : Gestion des événements système.
- **Notification Manager** : Gestion des notifications Android.

## 🗂️ Structure Principale

- **MainActivity** : Liste des dépenses et accès à l’ajout de nouvelles transactions.
- **AddTransactionActivity** : Formulaire pour ajouter une nouvelle dépense.
- **EditTransactionActivity** : Interface pour modifier une dépense existante.
- **TransactionDetailsActivity** : Affichage détaillé d'une transaction avec option de suppression.
- **NotificationUtil & ReminderReceiver** : Système de notifications pour rappels réguliers.

## 🛠️ Base de Données

Utilisation de **Room** pour stocker :
- Le montant de la dépense
- La catégorie associée
- La date de la dépense

## 🚀 Installation

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/mohamedazizchebil/BudgetFlow.git
