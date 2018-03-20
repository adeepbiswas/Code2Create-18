package acm.event.codetocreate18.Model.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import acm.event.codetocreate18.R;
import acm.event.codetocreate18.View.Fragments.AboutFragment;


public class DataGenerator {


   // public static String ab=new String();
    //public static String bc=new String();
    //String  Date[] = new String[18];
    private static final String TAG = "DataGenerator";

    public DataGenerator()
    {}
    public DataGenerator(String text1, String text2){

    }

    public DataGenerator(String text1,String text2,String text3,String text4){

    }

    public String splashData(int i) {
        String text[] = {"Innovate.Learn.", "Create."};
        return text[i];
    }

    public static String scanMessage(String x)
    {
        String message=x;
        return message;
    }

    public String[] getQuestions()
    {
        String Ques[] = {"Who all can register?","What are the registration fees?","How many people can I have on my team?",
                "What tracks does this hack include?", "How do I choose my track?" , "How do I qualify for the pitching round?", "Who will be on the Judging panel? "
                , "What about accommodation for externals? ", "What all can I win?", "What if I get hungry or need internet ? "};
        return Ques;
    }

    public String[] getAnswers()
    {
        String Ans[] = {"Any and all students from all over the country can register. We welcome you all!",
                "Nothing, it’s absolutely free!",
                "You can have 1 to 4 people per team. You should either create your own team or accept invitations from other team admins.",
                "Code2Create incorporates four main areas of interest: \n\n" +
                        "-FinTech \n" +
                        "-Smart Cities \n" +
                        "-Education \n" +
                        "-Health Care\n"+
                        "Space","When you register we will take your preferences for each track and allot your track on a first-come-first-served basis. You will also select a problem statement from a given set."
                ,"There will be a technical inspection on Day 2 during the hack. After the inspection, we will shortlist 120 teams. These 120 teams will get a chance to present their hack in the expo for evaluation. ",
                "We have experts from IBM, ACM India and Startup ecosphere to look into the details of your amazing projects. ",
                "We will not be providing any accomodation for external students. We expect our participants to stay at the venue for the full hack. "
                ,"Cash prizes and internships for first three teams, T-Shirts, certificates for participants, Digital Ocean credits, and stickers.",
                "We’ll provide you with delicious food at regular intervals (breakfast, lunch, dinner and snacks) along with beverages. Also, you will be provided with free access to our beloved internet facility, VOLSBB.",};
        return Ans;
    }

    public static String getTimelineEvent(int i)
    {
      //  final ArrayList<String>  event = new ArrayList<String>();
        final String Event[] = {"Registration","Orientation","Opening Ceremony","Pitch Pits (Team Formation)",
                "Hack Starts","Dinner","Quiz","Late Night snacks","Nap Time",
               "Coffee Time","Breakfast","Session-1","Session-2",
        "Lunch","Technical Inspection","Snacks","Elimination","Music","Dinner","Jenga Challange","Late Night Snacks","Nap Time","Coffee Time","Alarming Call","Breakfast","Hack Ends","Expo","Experience Sharing","Closing Ceremony","Hey go away","Its over !"};

       /* DatabaseReference timelineReference = FirebaseDatabase.getInstance().getReference().child("Timeline");
        timelineReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();

                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Timeline").child(key);
                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String event1 = dataSnapshot.child("Event1").getValue(String.class);
                            event.add(event1);
                            String event2 = dataSnapshot.child("Event2").getValue(String.class);
                            event.add(event2);
                            String event3= dataSnapshot.child("Event3").getValue(String.class);
                            event.add(event3);
                            String event4= dataSnapshot.child("Event4").getValue(String.class);
                            event.add(event4);
                            String event5= dataSnapshot.child("Event5").getValue(String.class);
                            event.add(event5);
                            String event6= dataSnapshot.child("Event6").getValue(String.class);
                            event.add(event6);
                            String event7= dataSnapshot.child("Event7").getValue(String.class);
                            event.add(event7);
                            String event8= dataSnapshot.child("Event8").getValue(String.class);
                            event.add(event8);
                            String event9= dataSnapshot.child("Event9").getValue(String.class);
                            event.add(event9);
                            String event10= dataSnapshot.child("Event10").getValue(String.class);
                            event.add(event10);
                            String event11= dataSnapshot.child("Event11").getValue(String.class);
                            event.add(event11);
                            String event12= dataSnapshot.child("Event12").getValue(String.class);
                            event.add(event12);
                            String event13= dataSnapshot.child("Event13").getValue(String.class);
                            event.add(event13);
                            String event14= dataSnapshot.child("Event14").getValue(String.class);
                            event.add(event14);
                            String event15= dataSnapshot.child("Event15").getValue(String.class);
                            event.add(event15);
                            String event16= dataSnapshot.child("Event16").getValue(String.class);
                            event.add(event16);
                            String event17= dataSnapshot.child("Event17").getValue(String.class);
                            event.add(event17);
                            String event18= dataSnapshot.child("Event18").getValue(String.class);
                            event.add(event18);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });
                }                       }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Read failed");
            }
        });*/
        return Event[i];

    }


    public static String getTimelineDate(int i)
    {
        //final String Date[]= new String[50];
        final String Date[] = {"2018-03-10 17:30","2018-03-10 18:30","2018-03-10 19:00","2018-03-10 20:00","2018-03-10 20:30","2018-03-10 21:00","2018-03-10 22:30",
                "2018-03-11 02:00","2018-03-11 02:30","2018-03-11 06:00","2018-03-11 08:00","2018-03-11 10:00","2018-03-11 12:00","2018-03-11 13:00","2018-03-11 15:00",
                "2018-03-11 18:00","2018-03-11 19:00","2018-03-11 19:30","2018-03-11 21:00","2018-03-11 23:00","2018-03-12 02:00","2018-03-12 02:30","2018-03-12 06:00",
                "2018-03-12 07:00","2018-03-12 08:00","2018-03-12 08:30","2018-03-12 09:00","2018-03-12 11:30","2018-03-12 12:00","2018-03-12 12:00","2018-03-12 12:00"};

        /*final ArrayList<String> Date = new ArrayList<String>();

        DatabaseReference timeReference = FirebaseDatabase.getInstance().getReference().child("Time");
        timeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();

                    DatabaseReference timekeyReference = FirebaseDatabase.getInstance().getReference().child("Time").child(key);
                    timekeyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String tim1 = dataSnapshot.child("Time1").getValue(String.class);
                            String tim2= dataSnapshot.child("Time2").getValue(String.class);
                            Date.add(tim1);
                            Date.add(tim2);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });
                }                       }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Read failed");
            }
        });

        //final String temp1=ab;
        //final String temp2=bc;

*/
        return Date[i];

    }

    public static OrderStatus checkOderStatus(String s1,String s2 ) {
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date1 = sdf.parse(s1);
            Date date2 = sdf.parse(s2);
            Calendar cal = Calendar.getInstance();
            Date sys = sdf.parse(sdf.format(cal.getTime()));
            if (sys.compareTo(date1) == -1)
                return OrderStatus.INACTIVE;
            else if (sys.compareTo(date2) == 0 || sys.compareTo(date2) == 1)
                return OrderStatus.COMPLETED;
            else
                return OrderStatus.ACTIVE;
        } catch (ParseException e) {
            return OrderStatus.INACTIVE;
        }
    }

    public  static  String getPrizeTitle(int i){
        String prizetitle[] = {"First Prize","Second Prize","Third Prize","ACM-W","Special Prize","Special Prize","Special Prize","Special Prize"};
        return prizetitle[i];
    }

    public static String getSponsorTitle(int i)
    {
        String title[] = {"Parent Organization","Diversity Sponsor","Diversity Sponsor","Cloud Sponsor","Cloud Sponsor","Cloud Sponsor",
                "Platform Sponsor", "Coding Partner", "Learning Partner","Domain Partner","Patron","Patron","Patron","Patron","Patron","Patron","Patron",
        "Patron","Patron","Patron","Patron","Patron","Patron","Patron","Patron","Patron","Patron","Patron"};
        return title[i];
    }

    public  static  int Prizepathtoimage(int i){

        int title[] = {R.drawable.prizes03,R.drawable.prizes04,R.drawable.prizes02,R.drawable.acmwlogo,R.drawable.sp10
                ,R.drawable.sp15,R.drawable.sp28,R.drawable.sp17,};
        return title[i];

    }

    public static  String getprizeDetail(int i){

        String prizeDetail[] = {"Cash Prize of INR 12000 for the winner","Cash Prize of INR 5000 for the runner up","Cash Prize of INR 2000 for the second runner up","Special Prize of INR 2000 for Women in Computing","Amazon gift card of INR 15000 for the best hack/website submitted on .tech domain","INR 5000 Special prize or a project on hasura platform","$100 for best API winner","Lifetime premium license for JetBrains IDEs"};
        return  prizeDetail[i];
    }
    public  static  String getprzieAmount(int i){
        String prizeAmount[] = {"INR 12000","INR 5000","INR 3000","INR 2000","INR 15000","INR 5000","$100","Lifetime premium"};
        return  prizeAmount[i];
    }

    public static int pathToImage(int i)
    {
        int title[] = {R.drawable.sp1,R.drawable.sp2,R.drawable.sp3,R.drawable.sp4,R.drawable.sp5
        ,R.drawable.sp6,R.drawable.sp7,R.drawable.sp8, R.drawable.sp9, R.drawable.sp10,R.drawable.sp11,R.drawable.sp12,R.drawable.sp13,R.drawable.sp14,
                R.drawable.sp15,R.drawable.sp16,R.drawable.sp17,R.drawable.sp18,R.drawable.sp19,R.drawable.sp20,R.drawable.sp21,R.drawable.sp22,
                R.drawable.sp23,R.drawable.sp24,R.drawable.sp25,R.drawable.sp26,R.drawable.sp27,R.drawable.sp28};
        return title[i];

    }

    public String[] getCouponTitles() {
        String[] couponTitles = new String[] {"C\nO\nF\nF\nE\nE", "B\nR\nE\nA\nK\nF\nA\nS\nT", "L\nU\nN\nC\nH", "S\nN\nA\nC\nK\nS", "D\nI\nN\nN\nE\nR"};
        return couponTitles;
    }

    public int[] getCouponPrimaryImages() {
        int[] couponsIds = new int[] {R.drawable.ic_coffee, R.drawable.ic_breakfast, R.drawable.ic_lunch, R.drawable.ic_snacks, R.drawable.ic_dinner};
        return couponsIds;
    }

    public enum OrderStatus {

        COMPLETED,
        ACTIVE,
        INACTIVE;

    }
    public enum Orientation {

        VERTICAL

    }

    public static AboutGroupModel getFacultyOrganaiser(AboutFragment aboutFragment) {
        ArrayList<AboutModel> childList=new ArrayList<>();
        AboutModel child1=new AboutModel();
        child1.setName("Dr. Aswani Kumar Cherukuri");
        child1.setDesignation("Dean\n" +
                "School of Information Technology & Engineering");
        child1.setImageResource(R.drawable.dean_site);
        AboutModel child2 = new AboutModel();
        child2.setName("Prof. H.R. Vishwakarma");
        child2.setDesignation("Faculty Sponsor\n" +
                "ACM VIT Student Chapter");
        child2.setImageResource(R.drawable.hrv);
        AboutModel child3 = new AboutModel();
        child3.setName("Prof. Divya Udayan");
        child3.setDesignation("Faculty Sponsor\n" +
                "ACM VIT Student Chapter");
        child3.setImageResource(R.drawable.divya);

        childList.add(child1);
        childList.add(child2);
        childList.add(child3);
        AboutGroupModel group = new AboutGroupModel(childList);
        group.setName("Faculty Organisers");
        return group;
    }

    public static AboutGroupModel getStudentOrganiser(AboutFragment aboutFragment){
        ArrayList<AboutModel> childList=new ArrayList<>();
        AboutModel children[] = new AboutModel[10];
        for(int i = 0; i < 10; i++)
            children[i] = new AboutModel();
        children[0].setName("Abhitej Singh");
        children[0].setDesignation("President");
        children[0].setContact(false);
        children[0].setImageResource(R.drawable.abhitej);
        children[1].setName("Hardika Goyal");
        children[1].setDesignation("Managing Director");
        children[1].setContact(false);
        children[1].setImageResource(R.drawable.hardika);
        children[2].setName("Sourish Banerjee");
        children[2].setDesignation("Technical Director");
        children[2].setContact(false);
        children[2].setImageResource(R.drawable.sourish);
        children[3].setName("Harshit Kedia");
        children[3].setDesignation("Associate Technical Head");
        children[3].setContact(false);
        children[3].setImageResource(R.drawable.harshit);
        children[4].setName("Yash Shah");
        children[4].setDesignation("Research and ICPC Head");
        children[4].setContact(false);
        children[4].setImageResource(R.drawable.yash);
        children[5].setName("Vinit Bodhwani");
        children[5].setDesignation("Associate Research Lead");
        children[5].setContact(false);
        children[5].setImageResource(R.drawable.vinit);
        children[6].setName("Shivam Chawla");
        children[6].setDesignation("Public Relations");
        children[6].setContact(false);
        children[6].setImageResource(R.drawable.shivam);
        children[7].setName("Rishabh");
        children[7].setDesignation("Operations Head");
        children[7].setContact(false);
        children[7].setImageResource(R.drawable.ri);
        children[8].setName("Anmol");
        children[8].setDesignation("Marketing Head");
        children[8].setContact(false);
        children[8].setImageResource(R.drawable.anmol);
        children[9].setName("Vibhore Gupta");
        children[9].setDesignation("Finance Head");
        children[9].setContact(false);
        children[9].setImageResource(R.drawable.vibhore);
        for(int i = 0; i < 10; i++) childList.add(children[i]);
        AboutGroupModel group= new AboutGroupModel(childList);
        group.setName("Student Organisers");
        return group;
    }

    public static AboutGroupModel getContacts(AboutFragment aboutFragment)
    {
        ArrayList<AboutModel> childList=new ArrayList<>();

        /*AboutModel child1=new AboutModel();
        child1.setName("Website");
        child1.setContact(true);
        child1.setImageResource(R.drawable.ic_website);
        child1.setDesignation("http://c2c.acmvit.in");
        child1.setPosition(1);

        AboutModel child2=new AboutModel();
        child2.setName("Facebook");
        child2.setContact(true);
        child2.setImageResource(R.drawable.ic_facebook);
        child2.setDesignation("http://www.facebook.com/acm.vitu");
        child2.setPosition(2);

        AboutModel child3=new AboutModel();
        child3.setName("Slack");
        child3.setContact(true);
        child3.setImageResource(R.drawable.slack);
        child3.setDesignation("http://www.slack.com/");
        child3.setPosition(3);*/

        AboutModel child4=new AboutModel();
        child4.setName("Mail");
        child4.setContact(true);
        child4.setImageResource(R.drawable.ic_email);
        child4.setDesignation("mailto:outreach@acmvit.in");
        child4.setPosition(4);

        AboutModel child5=new AboutModel();
        child5.setName("Call");
        child5.setContact(true);
        child5.setImageResource(R.drawable.ic_call);
        child5.setDesignation("tel:918279887513");
        child5.setPosition(5);


        //childList.add(child1);
        //childList.add(child2);
        //childList.add(child3);
        childList.add(child4);
        childList.add(child5);
        AboutGroupModel group = new AboutGroupModel(childList);
        group.setName("Contact us");
        return group;
    }

    public String[] quizQuestions() {
        String[] quizQuestions = new String[] {
                "What is the jumper setting on a SCSI device to configure it to use the fourth SCSI id?",
                "What tool is used to test serial and parallel ports?",
                "What device prevents power interruptions, resulting in corrupted data?",
                "A sound card typically uses which IRQ?",
                "What form of transmissions do modems use?",
                "Which of the following is NOT one of the four major data processing functions of a computer?",
                "What tag, when placed on an animal, can be used to record and track all its movements?",
                "Surgeons can perform delicate operations by manipulating devices through computers instead of manually. This technology is known as:",
                "Technology no longer protected by copyright, available to everyone, is considered to be",
                "What is the study of molecules and structures whose size ranges from 1 to 100 nanometers?",
                "Science that attempts to produce machines that display the same type of intelligence as humans:",
                "Data that has been organized or presented in a meaningful fashion:",
                "The name for the way that computers manipulate data into information is called:",
                "Computers gather data, which means that they allow users to ____________ data.",
                "After a picture has been taken with a digital camera and processed appropriately, the actual print of the picture is considered:",
                "Computers use the ____________ language to process data.",
                "Computers process data into information by working exclusively with:",
                "In the binary language, each letter of the alphabet, each number and each special character is made up of a unique combination of:",
                "What does FDISK do?",
                "Which of the following conditions most increases the likelihood that ESD will occur?",
                "Which IRQ does the hard disk controller commonly use?",
                "IRQ 6 is commonly assigned to:",
                "Which of the following is NOT a type of RAM?",
                "Which is NOT typically a Field Replaceable Unit?",
                "How many pins are present on a VGA?",
                "What component would most likely cause a parity error?",
                "How many devices can be used on a single SCSI bus?",
                "What helps prevent power surges?",
                "How many pins do IDE cables have?",
                "The first mechanical computer designed by Charles Babbage was called?"
        };
        return quizQuestions;
    }

    public String[][] quizQuestionOptions() {
        String[][] quizOptions = new String[][]{
                {"010", "110", "011", "101", "001"},
                {"High volt probe", "Cable scanner", "Loop backs (wrap plugs)", "Sniffer"},
                {"Battery back-up unit", "Surge protector", "Multiple SIMMs strips", "Data guard system"},
                {"6", "5", "15", "1"},
                {"Synchronous", "Asynchronous", "Timed interval", "Bank"},
                {"Gathering data", "Processing data into information", "Analyzing the data", "Storing the data"},
                {"POS", "RFID", "PPS", "GPS"},
                {"Robotics", "Computer forensics", "Simulation", "Forecasting"},
                {"Proprietary", "Open", "Experimental", "In the public domain"},
                {"Nanoscience", "Microelectrodes", "Computer forensics", "Artificial intelligence"},
                {"Nanoscience", "Nanotechnology", "Simulation", "Artificial intelligence"},
                {"Process", "Software", "Storage", "Information"},
                {"Programming", "Processing", "Storing", "Organizing"},
                {"Present", "Input", "Output", "Store"},
                {"Data", "Output", "Input", "Process"},
                {"Processing", "Kilobyte", "Binary", "Representational"},
                {"Multimedia", "Words", "Characters", "Numbers"},
                {"8 bytes", "8 kilobytes", "8 characters", "8 bits"},
                {"Performs low-level formatinf of hard drive", "Fixes bad sectors on hard drive", "Recovers lost clusters on hard drive", "Creates partitions on hard drive"},
                {"Hot, dry conditions", "Cool, damp conditions", "Cool, dry conditions", "Hot, damp conditions"},
                {"14", "1", "2", "11"},
                {"Sound card", "COM1", "Floppy drive controller", "LPT1"},
                {"SIMM", "DIMM", "ROM", "SLIPP"},
                {"System ROM", "Power supply", "System chassis", "Video controller"},
                {"15", "9", "25", "32"},
                {"Bad hard disk", "Bad controller", "Bad RAM", "Bad software"},
                {"1", "8", "20", "10"},
                {"Surge processor", "Spike protector", "UPS system", "High-grade multimeter"},
                {"25", "50", "100", "40"},
                {"Abacus", "Analytical Engine", "ENIAC", "IAS Machine"}
        };
        return quizOptions;
    }

    public int[] correctOptions() {
        int[] correctOptions = new int[] {
                2, 2, 0, 1, 1, 2, 1, 0, 0, 0,
                3, 3, 1, 1, 1, 2, 3, 3, 3, 0,
                0, 2, 2, 2, 0, 2, 1, 0, 3, 1
        };
        return correctOptions;
    }

    public QuizQuestionModel[] getQuizDatabase() {
        QuizQuestionModel[] quizDatabase = new QuizQuestionModel[30];
        String[] questions = quizQuestions();
        String[][] options = quizQuestionOptions();
        int[] correctOptions = correctOptions();
        for(int i = 0; i < quizDatabase.length; i++)
            quizDatabase[i] = new QuizQuestionModel(questions[i], options[i], correctOptions[i]);
        return quizDatabase;
    }
}
