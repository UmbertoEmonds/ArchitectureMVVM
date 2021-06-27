package fr.projet2.mvvm.util.injection

class Injection {

    companion object {
        fun provideViewModelFactory(): ViewModelFactory {
            return ViewModelFactory()
        }
    }

}