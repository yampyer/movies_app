package co.jeanpidev.wifiestamovies.di

import javax.inject.Scope
import kotlin.annotation.MustBeDocumented
import kotlin.annotation.Retention

/**
 * Scopes TL;DR:
 * No scope = new instance created every time
 * [@Singleton] = only one instance
 * [@CustomScope] = instance reused depending on the component’s lifecycle
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped
