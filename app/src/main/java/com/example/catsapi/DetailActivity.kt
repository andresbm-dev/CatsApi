package com.example.catsapi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.catsapi.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import java.net.URI

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_detail)

        val informacion = intent.getStringExtra("INFORMACION")
        val url = intent.getStringExtra("URL")
        val name = intent.getStringExtra("NAME")
        val photo = intent.getStringExtra("PHOTO")

        /*val info = findViewById<TextView>(R.id.tvdescription)
        url_ = findViewById(R.id.tvurl)
        val name_ = findViewById<TextView>(R.id.tvname)*/
        val photo_ = binding.ivphoto

        binding.tvdescription.text = informacion
        binding.tvurl.text = url
        binding.tvname.text = name
        Picasso.get().load(photo).into(photo_);
        println("$url")
        navegacionLink(url)
    }

    private fun navegacionLink(url:String?) {
        binding.tvurl.setOnClickListener {
            val link = Uri.parse(url)
            println(link)
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)

        }


    }
}