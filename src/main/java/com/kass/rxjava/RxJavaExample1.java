package com.kass.rxjava;

import org.apache.cxf.jaxrs.client.WebClient;
import rx.Emitter;
import rx.Observable;

import org.apache.cxf.jaxrs.rx.client.ObservableRxInvoker;
import org.apache.cxf.jaxrs.rx.client.ObservableRxInvokerProvider;
import rx.Observable;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


/**
 * Created by Kassem A. Ali on 2018-02-23.
 */
public class RxJavaExample1 {

    public static void main(String[] args) {
        try {

            RxJavaExample1 rxj = new RxJavaExample1();

            Observable.just("Hello", "World", "kass")
                    .subscribe(System.out::println);

            Observable.range(1, 10).filter(i -> i % 2 == 1).subscribe(System.out::println);

            rxj.testRxJava2ObservableWithCxfWebClient();

            fibsObservable().subscribe(s -> System.out.println("Result: "+s), (t) -> t.printStackTrace());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void testRxJava2ObservableWithCxfWebClient() throws Exception {
        String myIpEndpoint = "https://api.ipify.org?format=json";

        WebClient webClient = WebClient.create(myIpEndpoint, Collections.singletonList(new ObservableRxInvokerProvider()));

        Observable<String> obs = webClient.accept("text/plain")
                .rx(ObservableRxInvoker.class)
                .get(String.class)
                // to see the order in which these methods are called
                .doAfterTerminate(() -> System.out.println("doAfterTerminate"))
                .doOnTerminate(()    -> System.out.println("doOnTerminate"))
                .doOnCompleted(()    -> System.out.println("doOnCompleted"));

        obs.subscribe(s -> System.out.println("Result: "+s), (t) -> t.printStackTrace());
    }

    public void testObservableJaxrs21With404Response() throws Exception {
        String address = "https://jsonplaceholder.typicode.com/posts/1";
        Invocation.Builder b = ClientBuilder.newClient()
                .register(new ObservableRxInvokerProvider())
                .target(address).request();

        b.rx(ObservableRxInvoker.class)
                .get(String.class)
                .subscribe(
                s -> {
                    System.out.print("Exception expected");
                },
                t -> t.printStackTrace());
    }



    public static Observable<String> fibsObservable(){
        //Observable.create(new AsyncOnSubscribe<String, String>() {
        //})
        return Observable.create(subscriber -> {
            subscriber.onNext("kass");
            subscriber.onNext("kass2");
            subscriber.onNext("kass3");
            subscriber.onNext("kass4");

        });
    }

}
