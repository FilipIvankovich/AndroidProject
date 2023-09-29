package hr.tvz.android.notesapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hr.tvz.android.notesapp.Database.NoteDatabase
import hr.tvz.android.notesapp.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class NoteViewModel(val app: Application) : AndroidViewModel(app) {
    private val repository : NotesRepository

    val allNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(app).getNoteDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
}
