import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.model.User
import com.example.skiva.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> get() = _registerStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun registerUser(name: String, email: String, phoneNumber: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _errorMessage.value = "Passwords do not match"
            return
        }

        val user = User(name, email, phoneNumber, password)

        // Panggil fungsi registerUser dari UserRepository
        repository.registerUser(user,
            onSuccess = {
                _registerStatus.value = true
            },
            onFailure = { error ->
                _errorMessage.value = error
            }
        )
    }
}
