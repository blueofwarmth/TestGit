import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormulaCalculator {
    // 将变量替换为数值
    public static String replaceVariables(String formula, Map<String, Double> variableValues) {
        //test
        // 定义正则表达式，匹配以字母开头的变量
        Pattern pattern = Pattern.compile("\\b[A-Za-z]+\\b");
        Matcher matcher = pattern.matcher(formula);
        
        // 创建一个新的字符串，用于存储替换后的公式
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String variable = matcher.group();
            Double value = variableValues.get(variable);
            if (value != null) {
                matcher.appendReplacement(result, value.toString());
            }
        }
        matcher.appendTail(result);
        
        return result.toString();
    }

    public static void main(String[] args) {
        // 原始公式
        String formula = "((A-AA/(1-8))/B*C*(1-E)+(AA+F)/(1-G)+H/EE+D)*(1-G)*I*1.13+J+D";
        
        // 定义变量及其对应的数值
        Map<String, Double> variableValues = new HashMap<>();
        variableValues.put("A", 100.0);
        variableValues.put("AA", 20.0);
        variableValues.put("B", 5.0);
        variableValues.put("C", 2.0);
        variableValues.put("D", 3.0);
        variableValues.put("E", 0.1);
        variableValues.put("EE", 50.0);
        variableValues.put("F", 10.0);
        variableValues.put("G", 0.2);
        variableValues.put("H", 15.0);
        variableValues.put("I", 1.0);
        variableValues.put("J", 8.0);
        
        // 将变量替换为数值
        String replacedFormula = replaceVariables(formula, variableValues);
        System.out.println("替换后的公式: " + replacedFormula);

        // 计算替换后的公式结果（这部分涉及到表达式计算）
        // Java 中没有直接计算字符串公式的内置方法，可以使用第三方库如 `javax.script.ScriptEngine`
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Object result = engine.eval(replacedFormula);
            System.out.println("计算结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
