import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skiva.repository.UserRepository

class InputObatViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InputObatViewModel::class.java)) {
            return InputObatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
