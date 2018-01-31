package examples.pubhub.rest;



public class RESTUtilities {

	public static PubHubRestClient getPubhubRestClient() {
		return new PubHubRestClientImpl();
	}
}
