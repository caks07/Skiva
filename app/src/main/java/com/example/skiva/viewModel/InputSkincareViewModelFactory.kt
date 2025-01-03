import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skiva.repository.UserRepository

class InputSkincareViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InputSkincareViewModel::class.java)) {
            return InputSkincareViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
