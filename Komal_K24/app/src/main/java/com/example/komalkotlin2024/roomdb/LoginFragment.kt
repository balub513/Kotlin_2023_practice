package com.example.komalkotlin2024.roomdb

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalkotlin2024.MainRecyclerViewAdapter
import com.example.komalkotlin2024.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var adapter: MainRecyclerViewAdapter
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModel1: LoginEmployeeViewModel

    private lateinit var recycleView: RecyclerView
    private lateinit var addBtn: Button
    private lateinit var logInBtn: Button
    private lateinit var passwordEt: EditText
    private lateinit var nameEt: EditText

    private var employeeList = ArrayList<String>()
    private var mainListOfEmployee = ArrayList<Employee>()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = EmployeeDatabase.getDatabase(requireContext()).employeeDao()
        viewModel1 = ViewModelProvider(this, LoginEmployeeViewModel.LoginEmployeeViewModelFactory(requireContext() as Application,repo))[LoginEmployeeViewModel::class.java]
       // viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

         nameEt = view.findViewById(R.id.name_edit)
         passwordEt = view.findViewById(R.id.password_edit)
         logInBtn = view.findViewById(R.id.login_btn)
         addBtn = view.findViewById(R.id.Add_btn)
         recycleView = view.findViewById(R.id.employee_recycle_view)

        recycleView.layoutManager = LinearLayoutManager(context)
             adapter =MainRecyclerViewAdapter(employeeList) {
                 CoroutineScope(Dispatchers.IO).launch {
                     viewModel.deleteEmployee(mainListOfEmployee[it])
                 }
        }
        recycleView.adapter = adapter

        viewModel.getAllEmployees()

        logInBtn.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                val name = nameEt.text.toString()
                val password = passwordEt.text.toString()
                if (!name.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.getEmployee(
                            nameEt.text.toString(),
                            passwordEt.text.toString()
                        )
                        viewModel.getAllEmployeesFlowable()
                    }
                }
            }
        })
        
        addBtn.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                val name = nameEt.text.toString()
                val password = passwordEt.text.toString()
                if (!name.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertEmployee(Employee(name = name, password = password))
                    }
            }

        }
        })

        viewModel.employees.observe(viewLifecycleOwner){
            Toast.makeText(context, " getAllEmployeesFlowable ${it.size} ", Toast.LENGTH_SHORT).show()
        }

        viewModel.getAllEmployees().observe(viewLifecycleOwner) { listofemp ->
            if (listofemp != null) {
                mainListOfEmployee = ArrayList(listofemp)
            var list: List<String>? = null
            CoroutineScope(Dispatchers.Main).launch {
                list = flow {
                    listofemp.onEach {
                        emit(it)
                    }
                }.map {
                    it.name
                }.flowOn(Dispatchers.IO)
                    .toList()

                list?.let {
                    employeeList.clear()
                    employeeList = ArrayList(it)
                    adapter.updateData(employeeList)
                    // adapter.updateData(employeeList)
                }
            }



            }

        }

        viewModel.employee.observe(viewLifecycleOwner){
            Toast.makeText(context, "${it.name} Login Success", Toast.LENGTH_SHORT).show()
        }

        viewModel.status.observe(viewLifecycleOwner){
            Toast.makeText(context, "${it} ", Toast.LENGTH_SHORT).show()

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}