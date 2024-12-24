import androidx.lifecycle.ViewModel
import com.example.skiva.model.DataObat
import com.example.skiva.repository.UserRepository

class InputObatViewModel(private val repository: UserRepository) : ViewModel() {

    fun saveObat(
        userId: String,
        obatList: List<DataObat>,
        waktuList: List<String>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        obatList.forEach { it.waktu = waktuList.joinToString(",") } // Gabungkan waktu sebagai string
        repository.saveObat(userId, obatList, onSuccess, onFailure)
    }

}

