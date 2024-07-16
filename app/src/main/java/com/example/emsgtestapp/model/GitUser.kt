package com.example.emsgtestapp.model

data class GitUser(
    val login: String,
    val id:Long,
    val nodeId:String,
    val avatarUrl:String,
    val gravatarUrl:String,
    val url:String,
    val htmlUrl:String,
    val followersUrl:String,
    val followingUrl:String,
    val gistsUrl:String,
    val starredUrl:String,
    val subscriptionsUrl:String,
    val organizationsUrl:String,
    val reposUrl:String,
    val eventsUrl:String,
    val receivedEventsUrl:String,
    val type: String,
    val siteAdmin:Boolean
)