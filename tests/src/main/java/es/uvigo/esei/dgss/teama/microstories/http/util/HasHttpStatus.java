package es.uvigo.esei.dgss.teama.microstories.http.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED;
import static javax.ws.rs.core.Response.Status.OK;

public class HasHttpStatus extends TypeSafeMatcher<Response> {
	private StatusType status;

	public HasHttpStatus(StatusType status) {
		this.status = status;
	}

	public HasHttpStatus(int statusCode) {
		this(Response.Status.fromStatusCode(statusCode));
	}

	@Override
	public void describeMismatchSafely(Response item, Description description) {
		description.appendText("was ").appendValue(item.getStatus());
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("status ").appendValue(this.status);
	}

	@Override
	protected boolean matchesSafely(Response item) {
		return this.status.getStatusCode() == item.getStatus();
	}

	@Factory
	public static HasHttpStatus hasHttpStatus(int statusCode) {
		return new HasHttpStatus(statusCode);
	}

	@Factory
	public static HasHttpStatus hasHttpStatus(StatusType status) {
		return new HasHttpStatus(status);
	}

	@Factory
	public static HasHttpStatus hasOkStatus() {
		return new HasHttpStatus(OK);
	}

	@Factory
	public static HasHttpStatus hasCreatedStatus() {
		return new HasHttpStatus(CREATED);
	}

	@Factory
	public static HasHttpStatus hasMethodNotAllowedStatus() {
		return new HasHttpStatus(METHOD_NOT_ALLOWED);
	}

	@Factory
	public static HasHttpStatus hasBadRequestStatus() {
		return new HasHttpStatus(BAD_REQUEST);
	}
}
