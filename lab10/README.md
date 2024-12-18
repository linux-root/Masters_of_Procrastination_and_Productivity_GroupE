## 1. Bug report generator
[source code](./prob1)
```java
package lesson10.labs.prob1.bugreporter;

import lesson10.labs.prob1.classfinder.ClassFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BugReportGenerator {
    private static final Logger LOG = Logger.getLogger(BugReportGenerator.class.getName());
    private static final String PACKAGE_TO_SCAN = "lesson10.labs.prob1.javapackage";
    private static final String REPORT_NAME = "bug_report.txt";
    private static final String REPORTED_BY = "reportedBy: ";
    private static final String CLASS_NAME = "classname: ";
    private static final String DESCRIPTION = "description: ";
    private static final String SEVERITY = "severity: ";
    public void reportGenerator() {
        List<Class<?>> classes = ClassFinder.find(PACKAGE_TO_SCAN);
        Map<String, List<String>> reports = new HashMap<>();

        for(Class<?> cl : classes) {
            if(cl.isAnnotationPresent(BugReport.class)) {
                BugReport report = cl.getAnnotation(BugReport.class);
                String reportLine = String.format("\n  reportedBy: %s\n  classname: %s\n  description: %s\n  severity: %s\n", report.reportedBy(), cl.getName(), report.description(), report.severity());
                String key = report.assignedTo();
                if (reports.containsKey(key)){
                    reports.get(key).add(reportLine);
                } else {
                    List<String> bugs = new ArrayList<>();
                    bugs.add(reportLine);
                    reports.put(key, bugs);
                }
            }
        }

        String filePath = "bug_report.txt";
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))){
            for(Map.Entry<String, List<String>> entry: reports.entrySet()){
                String line = entry.getKey() + String.join("\n", entry.getValue());
                System.out.println(line);
                printWriter.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
```
---

## 2. FixThis
[source code](./prob2)
```java
List<String> processList(List<String> list) {
    return list.stream().map(x -> {
        try {
            return doNothingIfShort(x);
        } catch (InputTooLongException e) {
            throw new RuntimeException(e);
        }
    }).collect(Collectors.toList());

}
```