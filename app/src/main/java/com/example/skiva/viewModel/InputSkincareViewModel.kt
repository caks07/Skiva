import androidx.lifecycle.ViewModel
import com.example.skiva.model.DataSkincare
import com.example.skiva.repository.UserRepository

class InputSkincareViewModel(private val repository: UserRepository) : ViewModel() {

    fun saveSkincare(
        userId: String,
        skincareList: List<DataSkincare>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.saveSkincare(userId, skincareList, onSuccess, onFailure)
    }
}
