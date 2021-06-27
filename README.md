# ArchitectureMVVM

Application Android utilisant l'architecture MVVM. Les appels réseaux se font via Retrofit et nous utilisons une API REST.

![mvvm](https://user-images.githubusercontent.com/16464109/123554708-89f1a480-d781-11eb-8269-1f658ef8cd15.png)

# Structuration des packages (à trouver dans app/src/main/mvvm) :

## activity

Ce package contient toutes les activités de l'application. Dans le cadre de MVVM, les activités ne servent qu'a mettre à jour le contenu de l'interface graphique.
Ces activités ne doivent plus contenir de logique métier.

## adapter

Ce package contient les adapter, classes qui permettent de définir et de gérer les cellules de nos RecyclerView (listes à élements recyclés).

## model

Ce package contient tous nos objets métiers, leur attributs portent le même nom que les données renvoyées par l'API pour assurer le parsing.

## repository

Ce package représente la couche d'accès aux données.
Le service expose et définit les requêtes de notre API (les URL et leur verbe HTTP).
Le repository se charge d'effectuer les requêtes HTTP et renvoi des objets de type MutableLiveData, ce sont des objets observables pour 
lesquels nous allons souscrire à leur changements depuis l'activité.

## utils

La classe ViewModelFactory est une factory qui permet de renvoyer une instance de ViewModel avec son repository dédié.
La classe Injection permet de renvoyer un ViewModelFactory qui ne doit être instancié qu'une seule fois (c'est un singleton).
La classe RetrofitBuilder permet de renvoyer un objet unique et construit de Retrofit avec notre URL de base d'API.

## viewmodel

Ce package contient toutes nos classes héritant de ViewModel, ces classes exposent des méthodes qui seront appélées directement depuis nos activités.
Ces méthodes (getUsers() et deleteUser(id: String)) font appel au repository dédié et renvoient des objets de type MutableLiveData, qui seront observées
dans nos activités. Le ViewModel (qui est référencé dans l'activité) garde en mémoire nos données chargées, elle survivent donc par exemple à une rotation de
l'écran ce qui nous permet de limiter nos requêtes HTTP...

