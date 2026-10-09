package sharedData.browser;

// reclamele Google de pe site acopera butoanele si uneori opresc navigarea cu o reclama pe tot ecranul;
// trimit domeniile lor spre o adresa inexistenta, asa ca nu se mai incarca deloc
// (merge si in paralel, si pe CI, spre deosebire de un profil de Chrome cu AdBlock)
public class AdBlocking {

    public static final String HOST_RULES = "--host-resolver-rules="
            + "MAP *.doubleclick.net 127.0.0.1,"
            + "MAP *.googlesyndication.com 127.0.0.1,"
            + "MAP *.googleadservices.com 127.0.0.1,"
            + "MAP *.adtrafficquality.google 127.0.0.1,"
            + "MAP fundingchoicesmessages.google.com 127.0.0.1";
}
