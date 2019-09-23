package com.capgemini.cn.demo.schedule.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListHaveArrayList {
	private List<ArrayList<Schedule>> myList;
}
