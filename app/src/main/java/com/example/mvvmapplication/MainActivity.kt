package com.example.mvvmapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmapplication.databinding.ActivityMainBinding
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var _mainBinding : ActivityMainBinding
    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mainBinding = DataBindingUtil.setContentView(this,  R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        loadData()
    }

    private fun loadData(){
        var request = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(IPostService::class.java)

        myCompositeDisposable?.add(
                request.getData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse))
    }

    private fun handleResponse(postModels: List<PostModel>) {
        val cardClickListenerImpl: CardClickListenerImpl = CardClickListenerImpl(this)
        val myRecyclerViewAdapter = MyRecyclerViewAdapter(postModels, cardClickListenerImpl)
        _mainBinding.myAdapter = myRecyclerViewAdapter

    }
}
