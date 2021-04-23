class PalindromeNumber {
    
    public boolean isPalindrome(int x) {
        if ( x < 0) {
            return false;
        } else if (x <= 9) {
            return true;
        }
        // 1.使用字符串解决
        // char[] charArray = String.valueOf(x).toCharArray();
        // int length = charArray.length >> 1;
        // for(int i = 0; i <= length; i++) {
        //     if (charArray[i] != charArray[charArray.length - 1 - i]) {
        //         return i == length;
        //     }
        // }
        // return true;

        // for (int i = 0; i <= x/2; i++) {
            
        // }

        // 2.使用求模 % 获取对应的位数，再对位数进行 N/2 次遍历
        List<Long> nums = new ArrayList<>();
        long n = 10;
        while (x % n != x) {
            nums.add((x % n) / (n / 10));
            n *= 10;
        }
        long temp = x % n;
        nums.add(temp / (n / 10));
        long length = nums.size() >> 1;
        for (int i = 0; i <= length; i++) {
            if (nums.get(i) != nums.get(nums.size() - 1 - i)) {
                return i == length;
            }
        }
        return true;
	
	//3.官方答案
	if(x < 0)
            return false;
        int cur = 0;
        int num = x;
        while(num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;

    }
}
