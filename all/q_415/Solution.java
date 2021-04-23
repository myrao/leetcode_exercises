class Solution {
    public static void main(String[] args) {
        System.out.println(addStrings("111", "999"));
    }

    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() <= 0) {
            return num2;
        }
        if (num2 == null || num2.length() <= 0) {
            return num1;
        }

        int offset = num1.length() - num2.length();
        if (offset > 0) {
            for (int i = 0; i < offset; i++) {
                num2 = "0" + num2;
            }
        } else if (offset < 0) {
            for (int i = 0; i < Math.abs(offset); i++) {
                num1 = "0" + num1;
            }
        }
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int sum = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int c1 = num1.charAt(i) - '0';
            int c2 = num2.charAt(i) - '0';
            int oneceSum = c1 + c2 + carry;
            System.out.println("oneceSum: " + oneceSum);
            sum = c1 + c2 + carry;
            carry = oneceSum / 10;
            ans.append(sum % 10);
        }
        if (carry > 0) {
            ans.append("1");
        }
        System.out.println("sum: " + sum);

        return ans.reverse().toString();
    }
}