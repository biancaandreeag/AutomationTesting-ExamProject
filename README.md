# AutomationTesting-ExamProject

Proiectul final pentru cursul de Testare Automată: teste automate pe site-ul
[automationexercise.com](https://automationexercise.com), un magazin online făcut pentru exersare.

## Ce testează

| Test | Ce verifică |
|---|---|
| `LoginTest` | login cu date greșite → mesajul de eroare (negativ) |
| `SignupNegativeTest` | înregistrare cu un email existent și cu un email invalid (negative) |
| `SearchTest` | căutarea de produse, date din XML (mai multe seturi) |
| `CartTest` | adaug un produs în coș → mesajul de confirmare → produsul, numele și prețul din coș |
| `AccountTest` | creez un cont nou → îl salvez și îl verific în MySQL → îl șterg de pe site |
| `ProductsApiTest` | API: lista de produse și căutarea, fără browser |

## Tehnologii

Java 26, Maven, Selenium 4, TestNG, Page Object Model (PageFactory), Log4j 2, ChainTest (raport),
MySQL (JDBC), REST Assured + Jackson, GitHub Actions.

## Structura

| Pachet | Rol |
|---|---|
| `pages` | câte o clasă pentru fiecare pagină (elementele și acțiunile ei), toate extind `BasePage` |
| `helperMethods` | `ElementMethods` (așteptări, click, completare), `JavaScriptMethods` (scroll, click prin JS) |
| `sharedData` | `ShareData`: deschide și închide browserul pentru fiecare test, screenshot la eșec |
| `sharedData/browser` | `BrowserFactory` alege Chrome sau Edge; setările fiecărui browser |
| `objectData` + `xmlReader` | datele de test din `src/test/resources/*.xml`, încărcate în obiecte |
| `configUtility`, `database`, `queries` | configurarea și conexiunea la MySQL, query-urile pe tabela `users` |
| `apiObjects` | obiectele în care se transformă răspunsurile API |
| `logger` | `LoggerUtility`: un log pentru fiecare test + `AllTests.log` |
| `tests`, `tests/api` | testele |

## Cum se rulează

```bash
mvn clean test                                        # toate testele (suites/allTests.xml)
mvn clean test -Dsuite=suites/smokeSuite.xml          # verificare rapidă: login + coș
mvn clean test -Dsuite=suites/regressionSuite.xml     # toate, în paralel (3 clase deodată)
mvn clean test -Dsuite=suites/apiSuite.xml            # doar API
mvn clean test -Dbrowser=edge                         # alt browser (implicit chrome)
```

`AccountTest` are nevoie de un MySQL local (root, fără parolă). Baza se creează o singură dată:

```bash
mysql -u root < src/test/resources/createTables.sql
```

**GitHub Actions:** tab-ul *Actions* → *Automation tests* → *Run workflow*, alegi suita și browserul.
Pe GitHub testele rulează headless (`-DCICD=true`), cu MySQL pornit lângă ele.

## Rezultate

- raportul: `target/chaintest/Index.html` (cu screenshot la testele picate)
- logurile: `target/logs/tests/` (câte unul pe test) și `target/logs/AllTests.log`
- pe GitHub: artefactul **test-report** din pagina rulării
