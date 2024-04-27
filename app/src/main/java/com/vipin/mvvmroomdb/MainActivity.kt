package com.vipin.mvvmroomdb
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipin.mvvmroomdb.addapter.TaskAdapter
import com.vipin.mvvmroomdb.databinding.ActivityMainBinding
import com.vipin.mvvmroomdb.model.Task
import com.vipin.mvvmroomdb.userViewModel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskAdapter = TaskAdapter()

        binding.recyclerViewTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                taskAdapter.submitList(it)
            }
        })

        binding.buttonAddTask.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                val task = Task(title = title, description = "")
                taskViewModel.insert(task)
                binding.editTextTitle.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
