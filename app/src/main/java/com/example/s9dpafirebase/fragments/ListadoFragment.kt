package com.example.s9dpafirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s9dpafirebase.R
import com.example.s9dpafirebase.adapters.CourseAdapter
import com.example.s9dpafirebase.models.CourseModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ListadoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_listado, container, false)

        val rvCursos: RecyclerView = view.findViewById(R.id.rvCursos)
        val db = FirebaseFirestore.getInstance()
        var lstCourses: List<CourseModel>
        //Obtenemos los documentos de la colección de Firestore
        db.collection("courses")
            .addSnapshotListener{snap,e->
                if(e!=null){
                    Snackbar
                        .make(view
                            ,"Error al obtener la colección"
                            , Snackbar.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
                lstCourses = snap!!.documents.map { documentSnapshot ->
                    CourseModel(documentSnapshot["description"].toString()
                                ,documentSnapshot["score"].toString())
                }
                rvCursos.adapter = CourseAdapter(lstCourses)
                rvCursos.layoutManager = LinearLayoutManager(requireContext())
            }
        return view
    }
}