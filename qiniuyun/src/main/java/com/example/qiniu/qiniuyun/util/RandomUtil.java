package com.example.qiniu.qiniuyun.util;

import java.util.*;

public class RandomUtil {

	public static String createRandom(int length) {
		String sRand = "";
		Random random = new Random();
		for (int j = 0; j < length; j++) {
			sRand = sRand + String.valueOf(random.nextInt(10));
		}
		return sRand;
	}

	/**
	 * @Title: getAccount @Description: 生成平台账户 @param 设定文件 @return void
	 *         返回类型 @throws
	 */
	public static String randomAccount(String temp) {
		temp = temp+"%s";
		return String.format(temp, System.currentTimeMillis(), createRandom(4));
	}
	/**
	 * @Title: getAccount @Description: 生成平台账户 @param 设定文件 @return void
	 *         返回类型 @throws
	 */
	public static String randomAccount(String temp, int length) {
		temp = temp+"%s";
		return String.format(temp, System.currentTimeMillis(), createRandom(length));
	}

    /**
     * 获取不重复的随机数
     * @param max 最大值（不包含此值）
     * @param size 获取个数
     * @return
     */
	public static List<Integer> randomNoRepetition(int max,int size){
	    List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < size - set.size(); ) {
            set.add(random.nextInt(max));
        }
        list.addAll(set);
        /**
         * 从大到小排序
         */
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = randomNoRepetition(50, 5);
        for (Integer integer : list) {
            System.out.println(integer);
        }

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        final List<Object> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < 100 ; j++) {
//                        String s = RandomUtil.randomAccount("%s");
//                        list.add(s);
//                        System.out.println(index+"---------"+s);
//                    }
//                }
//            });
//        }
    }


}
