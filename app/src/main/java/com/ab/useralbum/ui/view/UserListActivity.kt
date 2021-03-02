package com.ab.useralbum.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.useralbum.R
import com.ab.useralbum.model.User
import com.ab.useralbum.ui.view.adapter.HeaderAdapter
import com.ab.useralbum.ui.view.adapter.UserAapter
import com.ab.useralbum.ui.view.viewmodel.UserViewModel
import com.ab.useralbum.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    private lateinit var adapter: UserAapter
    private lateinit var header: HeaderAdapter

    private val viewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            setupUI()
            setupObservers()
        }



        private fun setupUI() {
            recyclerView.layoutManager = LinearLayoutManager(this)

            adapter = UserAapter(arrayListOf())
            header = HeaderAdapter()

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )

            val concatAdapter = ConcatAdapter(header, adapter)

            recyclerView.adapter = concatAdapter
        }

        private fun setupObservers() {
            viewModel.users.observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users)
                                header.updateUserNumber(users.size)
                            }
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
