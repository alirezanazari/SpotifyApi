package ir.alirezanazari.domain.intractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class UseCase<T, P>(private val io: Scheduler, private val ui: Scheduler) {

    private var disposable: CompositeDisposable = CompositeDisposable()

    abstract fun build(param: P): Observable<T>

    fun execute(observer: DisposableObserver<T>, param: P) {

        val observable: Observable<T> = build(param)
            .subscribeOn(io)
            .observeOn(ui)

        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(dispos: Disposable) = disposable.add(dispos)

    fun clearDisposable() {
        if (!disposable.isDisposed) disposable.dispose()
    }

}