package es.uvigo.esei.dgss.teama.microstories.service.util.security;

import java.util.function.Supplier;

import javax.ejb.Local;

@Local
public interface RoleCaller {
	public <V> V call(Supplier<V> supplier);

	public void run(Runnable run);

	public <V> V throwingCall(ThrowingSupplier<V> supplier) throws Throwable;

	public void throwingRun(ThrowingRunnable run) throws Throwable;

	public interface ThrowingRunnable {
		public void run() throws Throwable;
	}

	public interface ThrowingSupplier<V> {
		public V get() throws Throwable;
	}
}
