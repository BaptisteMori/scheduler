
package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;

class Main {

  public static void main (String[] args) throws IOException {

    System.out.println("______________________________\n");
		ScheduleReader test = new ScheduleReader();
		Map<String,Activity> test2 = test.readActivities("edt_java/activities.txt");
    System.out.println("______________________________\n");
  }
}
