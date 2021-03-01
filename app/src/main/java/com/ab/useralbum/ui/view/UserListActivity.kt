package com.ab.useralbum.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.useralbum.R
import com.ab.useralbum.data.RetrofitBuilder
import com.ab.useralbum.model.User
import com.ab.useralbum.ui.base.ViewModelFactory
import com.ab.useralbum.ui.view.adapter.UserAapter
import com.ab.useralbum.ui.view.viewmodel.UserViewModel
import com.ab.useralbum.utils.Status
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*

class UserListActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            setupViewModel()
            setupUI()
            setupObservers()
        }


        private fun setupViewModel() {
            viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
            ).get(UserViewModel::class.java)
        }

        private fun setupUI() {
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = UserAapter(arrayListOf())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }

        private fun setupObservers() {
            viewModel.getUsers().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users) }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }

        private fun retrieveList(users: List<User>) {
            adapter.apply {
                addUsers(users)
                notifyDataSetChanged()
            }
        }
    }
