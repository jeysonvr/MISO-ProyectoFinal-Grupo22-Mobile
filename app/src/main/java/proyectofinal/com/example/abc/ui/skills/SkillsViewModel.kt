package proyectofinal.com.example.abc.ui.skills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption
class SkillsViewModel :ViewModel() {

    private val _listSkillsAvailable = MutableLiveData<List<ComboOption>>()
    val listSkillsAvailable: LiveData<List<ComboOption>> = _listSkillsAvailable
    private val _listSkillsSelected = MutableLiveData<List<ComboOption>>()
    val listSkillsSelected: LiveData<List<ComboOption>> = _listSkillsSelected

    fun onListSkillsSelectedChanged(listSkillsSelected: List<ComboOption>) {
        _listSkillsSelected.value = listSkillsSelected
    }

    fun onClickErased(id: Int) {
        _listSkillsSelected.value = _listSkillsSelected.value!!.filter { it.id != id }
    }

}