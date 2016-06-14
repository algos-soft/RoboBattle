import java.awt.*;

public interface Bot {

	/**
	 * Restituisce il nome del bot.
	 * @return il nome del bot
	 */
	String getNome();

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	String getSlogan();


	/**
	 * Restituisce il colore del bot.
	 * @return il colore del bot
	 */
	Color getColore();


	/**
	 * Inverte le lettere di una parola.
	 * @param in la parola in ingresso
	 * @return la parola scritta al contrario.
	 */
	String inverti(String in);

	/**
	 * Calcola la checksum di una parola.
	 * Determina il valore di ogni carattere e restituisce la somma totale.
	 * @param in la parola in ingresso
	 * @return la checksum
	 */
	int checksum(String in);

	/**
	 * Decripta una parola in base a una chiave data.
	 * @param in la parola da decrittare
	 * @param key la chiave
	 * @return la parola decrittata.
	 * La somma delle vocali contenute nella chiave dà un numero segreto.
	 * Alle lettere in posizione pari va aggiunto il numero segreto
	 * Alle lettere in posizione dispari va sottratto il numero segreto
	 */
	String decrypt(String in, String key);



	public static final String[] STRINGHE = { "telegrafato", "metallografico", "scacchiera", "perpendicolarità",
			"tepidario", "ventottenne", "plantigrado", "posero", "lillipuziano", "scioline", "deportare", "usualità",
			"radiotelegrafista", "input", "amministrare", "cadranno", "disaminare", "absidata", "solido", "inserto",
			"piramide", "imbasciata", "uvea", "inflitta", "desioso", "rinunziabile", "incarbonchisce", "zoppicare",
			"nascondibile", "intrigare", "diffusibile", "frangiluce", "cresime", "incoronante", "menando", "direbbero",
			"approntamento", "volt", "riammalando", "microcitemia", "tolgano", "capocentro", "senatoconsulto", "aperta",
			"trancista", "temporario", "pacificazione", "mastio", "immorsatura", "odorando", "Camerun", "spatico",
			"trafile", "Tiberio", "ciabattona", "acherontea", "rilasciando", "sirtici", "accomunante", "carrozzare",
			"plutone", "predisporsi", "contestatario", "moffetta", "agrimensura", "posticipato", "arrischiare",
			"patentare", "canasta", "rifrugato", "opporreste", "ineffettuabilità", "stoccato", "Edimburgo", "cheliceri",
			"pseudogravidanza", "miagolando", "biconsonantico", "germinazione", "inguaribile", "Kurdistan",
			"miticizzato", "spannare", "cogliona", "tartassamento", "brugi", "sinizesi", "distorco", "ammaestrando",
			"vestiarista", "softball", "fiorentino", "affannato", "inferire", "telefonia", "varrai", "iperrealista",
			"lessicografo", "gerontologia", "tioacido", "riesportazione", "cogliere", "automobilina", "disintegrazione",
			"avvolse", "preoccupato", "irresoluto", "slang", "Fogazzaro", "transcutaneo", "guatemalteco", "pittore",
			"bullo", "metafisicamente", "imbrattato", "rimpiazzato", "siringato", "refrigerando", "deterrente",
			"appuntiscano", "circuizione", "ruppe", "polimerico", "acquamarina", "stoicismo", "vermicolare", "lembo",
			"contrafforte", "vulgato", "alloctona", "supervisionare", "verbasco", "ambigua", "livellare", "cocomero",
			"basando", "ponderabilità", "micronutriente", "vincheto", "creando", "picchiettare", "sanctus",
			"trentamila", "protestantesimo", "soffregare", "asserente", "ritiratisi", "agguerrendo", "benediva",
			"Ippocrate", "vecchio", "Camus", "monostele", "rasoiata", "discorsino", "ponderato", "contraggo",
			"salpingite", "riscontrare", "profane", "recluderete", "butirrico", "spiino", "fognaiolo", "ripescaggio",
			"ottenebrante", "arsero", "impiallacciatrice", "leggi", "soleggiare", "squittisca", "negoziare",
			"riassumendo", "paccottiglia", "svigorisce", "dilavando", "saccheggiare", "orinato", "dioico",
			"argomentazione", "incongrua", "spettinato", "ganghista", "sgraffignare", "miscelato", "attutirsi",
			"obiettivismo", "sadomaso", "gagliardetto", "diminuzione", "ispezionabile", "emanando", "troupe",
			"disimpegnando", "rasciugamento", "inesatto", "invalidazione", "depauperamento", "celidonia", "conferito",
			"contraete", "tergali", "temperamentale", "volteggiato", "tradurrete", "stampaggio", "corporativista",
			"chemioterapico", "resistibile", "iugulatore", "paracadute", "modulario", "sticometro", "cancan",
			"incontrovertibilità", "patibolo", "leve", "microproiettore", "patrilineare", "disruptiva", "tratterreste",
			"museale", "prispolone", "stiracchiare", "inattivato", "manovratrice", "stranendo", "guanteria",
			"aconitina", "acclive", "Gallura", "incollaggio", "accomodante", "valalla", "infusione", "immusonivi",
			"annuario", "cariofillato", "insettivora", "insidioso", "insignorendo", "colato", "arrotolato", "vanghetta",
			"riagganciato", "troncare", "diciassettenne", "periostraco", "irrancidisca", "ramazzare", "collimatore",
			"ritrattatrice", "servigio", "insospettendo", "otterremo", "birillo", "amnios", "assimilatore",
			"annacquato", "Eulero", "acetonica", "ammetterla", "smunga", "nicotina", "pseudoermafroditismo", "turbato",
			"preposizionale", "Paolo", "invendicata", "preservato", "alleviato", "trascesero", "lobotomizzato",
			"antiproibizionista", "resuscitato", "colludono", "scrollare", "sfogliare", "digitalizzato", "smungessero",
			"alesante", "cerebrospinale", "avvinceste", "esorcista", "fruttuoso", "transare", "canapicolo",
			"lazzeretto", "allignante", "morello", "sconquassare", "propinato", "teredine", "sciacquando", "frenato",
			"concento", "epirrema", "modulatore", "rubricatrice", "riscontrando", "stalagmometro", "smobilitare",
			"schizogenico", "ereditando", "emostasi", "sparizione", "antipatico", "riannunziando", "dissento", "treno",
			"ristorazione", "allegrezza", "sellaio", "ottenere", "lobby", "innovato", "realizzato", "attraevamo",
			"Anselmo", "trepestii", "abietta", "moralista", "apache", "corpulento", "acetificare", "supponevo",
			"tosone", "monadelfo", "minor", "linfoide", "sciagurataggine", "impigrirsi", "cripte", "epistemologa",
			"disonestà", "stracossero", "spiritello", "monospermia", "nitruro", "interattiva", "felicitare",
			"periscopio", "gamete", "blasto", "sienite", "papilla", "comparso", "pollino", "semoloso", "barracano",
			"binatoio", "quadragesimo", "commestibilità", "daltonico", "monitor", "amniocentesi", "protofillo",
			"librare", "indissi", "ambiscano", "epifisi", "fatato", "innamorare", "manganina", "fiondando",
			"passivismo", "bipolide", "accapezzato", "vettoriale", "clavaria", "circoncisione", "spallare", "bottale",
			"neoliberista", "allobiologica", "specchietto", "britannico", "indottrinando", "baldanza", "soggiacque",
			"intertedesco", "ludico", "riottenuto", "enervare", "capoguardia", "inestinta", "abbazia", "gemito",
			"neurochirurgico", "Pluto", "selenografo", "fitologia", "sprovvistane", "proscioglierò", "sericina",
			"diversificare", "dissipatezza", "polonia", "indeprecabile", "permutatrice", "inviperente", "fotografico",
			"vaporizzatore", "enfisema", "nefelometro", "addrizzato", "informe", "anticrittogamico", "vulnerabile",
			"tessendo", "marioleria", "ganda", "Mississippi", "sgualcitura", "Davide", "apparentare", "adempiuto",
			"torinista", "echinococco", "ritogliere", "didattico", "discorse", "trotterellato", "asemantica", "tenesmo",
			"gravidanza", "disdiciate", "radiodiffuso", "tergali", "difensivo", "succubo", "detective", "resine",
			"snodabile", "debuttare", "endemia", "teosofia", "accattivando", "quagliere", "obbiettivamente",
			"biasimevole", "nemmanco", "aperiodica", "bullone", "piombare", "anteporne", "divelliate", "lacrime",
			"europeo", "polimelodico", "dipinto", "borsaia", "stagnatore", "dimezzato", "sinterizzando", "smungeresti",
			"vascolarizzazione", "batoidei", "sovvenzionando", "galoppatoio", "spicciato", "maledirò", "riconsegnando",
			"infiorettante", "diaconessa", "implicitissima", "nichelare", "visigoto", "novellista", "tecnicità",
			"mutria", "broccoletto", "fiacchezza", "copule", "anteponevano", "gennaio", "uscendo", "impugnando",
			"nectonico", "banditrice", "circonverresti", "canton", "monofonditrice", "vitellino", "sollevazione",
			"mite", "livellabile", "tauriforme", "transato", "fustellatrice", "spermio", "lungomare", "ottenersi",
			"ghermitore", "ronzare", "imbattibile", "infervorando", "cicalato", "tricliniare", "frenatore", "Rebecca",
			"assolare", "bruscare", "delitto", "anzidetto", "commelinacea", "arrecatore", "coltivabile", "budgetario",
			"dequalificato", "saldando", "picnic", "localistico", "allevato", "pescarese", "ingiudicato", "retinite",
			"campestre", "sovrasterzato", "profumato", "radiolarite", "quarantenne", "immeritevole", "capocellula",
			"rilegare", "fiocine", "surgelare", "scorridore", "crudo", "autoriduttore", "omeliario", "angolari",
			"quinquagenario", "paternalista", "emesso", "improferibile", "miglia", "silografico", "esiliato",
			"prestampare", "interdizione", "clonando", "motivabile", "rovinii", "gommapiuma", "spettrografico",
			"strale", "seriografia", "adibente", "esonerando", "deformabile", "ossuto", "omeosmotico", "risaputo",
			"infoderando", "impallidire", "sfoltitrice", "plagiare", "neurologa", "scucimmo", "ischemizzando",
			"messevi", "bottame", "costruendo", "liberalizzando", "elettroterapia", "epicicloide", "interporremmo",
			"infornante", "anastigmatica", "nastrare", "capoversi", "versoio", "irrompendo", "antipatia", "sbassato",
			"delibazione", "soprafinestra", "frolle", "abbonamento", "marginalizzando", "sagrestia", "rinfocato",
			"rinsaldando", "immalinconire", "egotismo", "rieducando", "triumviro", "fluviale", "cionco", "emissaria",
			"almanaccare", "forchettone", "imperscrutabilità", "amminico", "coglierci", "tamponando", "armistiziale",
			"odontolito", "sganascione", "immaginifico", "default", "donde", "insonorizzare", "radarterapia",
			"microscheda", "oltremontana", "Savino", "iperonimo", "schiudendo", "antipaticissima", "bivieri",
			"anabolizzante", "anossia", "bicarbonato", "igrometrica", "fibra", "estravaganza", "sostituibile",
			"unitariano", "detersi", "appartando", "granatino", "convezione", "pastone", "forbiciaio", "arctoideo",
			"soluzione", "appannare", "arrovellato", "rendicontando", "ecumene", "cinquantenne", "basco", "bidonista",
			"spiccace", "ammonisca", "braccando", "infettività", "epistemologico", "insolentente", "deputazione",
			"ghiaione", "anconetana", "divelleva", "utilizzatore", "cantoria", "imbracato", "decongelando",
			"besciamella", "pennelleggiando", "insospettendo", "comunella", "spossamento", "orango", "interponeste",
			"periferia", "maomettano", "disambiguare", "sopravvivranno", "vocalizzato", "volleando", "tesaurizzatrice",
			"arida", "novazione", "anecoica", "spoderando", "strippare", "riassorbirsi", "velleità", "Cosimo",
			"volantinaggio", "sprovincializzato", "Afragola", "incarbonchire", "sussurrio", "davate", "circondurre",
			"richiedibile", "libecciata", "scabbioso", "tixotropico", "atticizzato", "lustrare", "decriptato", "così",
			"sovrapposto", "ubiquitario", "ortogenetica", "abduca", "trattatello", "attivatore", "viscoso", "creabile",
			"Enrico", "pompeggiato", "indelicato", "onomatopeico", "brancando", "approntante", "iperrealista",
			"padellaio", "gappista", "telesismo", "incomposta", "basettone", "retromarcia", "motivetto", "porremo",
			"coito", "schivato", "ritualità", "idolatrico", "assemblato", "manterrò", "relazionalità", "scoperchiando",
			"negoziando", "colorando", "iodo", "australiana", "trottatrice", "investigatore", "infoscando",
			"arteriografia", "respirabile", "diniego", "monete", "minsero", "girigogolo", "avvicendamento", "ignobile",
			"ambrato", "vaporizzatore", "buonora", "ostello", "punzecchiando", "occiduo", "sciabole", "millivolt",
			"eccoselo", "sconvolgere", "cossero", "battimare", "mitico", "buonemani", "ingobbito", "malavvezzo",
			"capeggiando", "ludico", "incendiato", "inforestierato", "rodiese", "collettivizzare", "sfruttato",
			"riscaldatura", "gasometro", "vanessa", "arrolato", "antimafia", "restauratore", "studicchiando",
			"pagnottella", "dattilografando", "trofologia", "bipartibile", "lappare", "assordamento", "combaciare",
			"spericolatezza", "fitofarmaci", "tetracromia", "perticare", "vecchina", "stralodando", "infranta",
			"captare", "cantare", "cinguettando", "impudico", "polese", "ragazzaccia", "candidare", "aggiuntatura",
			"cartamodello", "stalagmometria", "avvertirla", "centrismo", "rintuzzando", "vigesimo", "omeotonico",
			"assaettando", "attraevate", "ocellato", "aggiudicatario", "convocazione", "trivellato", "cinto",
			"canonista", "arrossendo", "cagnotto", "nutriscitelo", "varranno", "romitorio", "inquisitrice", "barriera",
			"vergogne", "multilaterale", "barbigi", "mangeria", "forestierismo", "miglior", "appieno", "starnutito",
			"rocchio", "ovolaccio", "rifasciato", "politrasfuso", "espungendo", "Ivrea", "azerbaigiano", "polistirolo",
			"consortile", "sovverrete", "user", "ravvisare", "condeterminato", "saggiatrice", "arelatense",
			"institorio", "ponilo", "manicure", "irrazionale", "slegatura", "sterzatura", "baracello", "Nora", "bureau",
			"elettoralismo", "streptomicina", "castrare", "idealeggiando", "nervetti", "ridisegnato", "rigirare",
			"polverume", "ottagonale", "fotofinish", "otturando", "fluorimetrico", "madia", "cocainismo",
			"scapicollato", "clic", "reflazionistico", "quintessenziando", "Nicoletta", "trasfosso", "bonificare",
			"deambulare", "cedersi", "arnione", "colombaccio", "sgombraneve", "eccelsa", "piantina", "racimolando",
			"uadi", "mullah", "membranaceo", "rimpiansero", "rinvelenendosi", "rutilare", "rimporterete", "cuoiame",
			"scudo", "addivengono", "contraddicente", "incamminando", "stazzando", "strussero", "rigettabile",
			"sventando", "clownesco", "sbellicare", "demodossologia", "pegmatite", "calcoloso", "tacciando", "chetato",
			"sbizzarrisce", "passivazione", "asfittico", "mancare", "iperbolica", "sostitutivo", "miocarditico",
			"transoceanico", "domificazione", "ininfiammabile", "aggrovigliare", "filobus", "sbrancare", "sbaraccare",
			"rincuorare", "etiologia", "ammusare", "scrosciando", "superstite", "accreditato", "infibulato",
			"stazzando", "fertilizzare", "immancabile", "verbigerazione", "abbaruffare", "istillante", "ambiando",
			"peccatrice", "incartatore", "impruando", "slavistica", "ricompresero", "erbaceo", "suffragetta",
			"immelensirsi", "pentalineo", "eguagliato", "trascinatrice", "temporizzando", "calorosità", "subordinativo",
			"incensurabilità", "ministrato", "spadroneggiare", "analizzala", "scacchiare", "antiporta",
			"avventurosissimo", "lussuoso", "filofascista", "neurotropo", "provinciale", "magnanina", "contagiare",
			"pecorone", "fustellare", "calesse", "sbianchimento", "circonvengono", "trazione", "infrascritta",
			"scavatura", "azzardare", "arbusto", "giurare", "frammentarietà", "moderando", "bromico", "scranna",
			"disabilitare", "sbirraglia", "bagnasciuga", "apostrofando", "pasquinata", "pluricampione", "ipotiroidea",
			"bancoposta", "traforare", "scissero", "pluriplano", "rabbino", "budino", "autotrofo", "lubrificativo",
			"anodina", "interalleata", "avvezzamento", "ritrattamento", "emarginare", "oneroso", "remeggiare",
			"immaginosa", "mulinando", "etmoidale", "derogazione", "strigliatura", "ibernazione", "seguace", "imitante",
			"duttilità", "popolarizzato", "eternando", "protomedico", "gravitone", "breakfast", "ingranato", "crown",
			"tamponamento", "epidemiologica" };

}
