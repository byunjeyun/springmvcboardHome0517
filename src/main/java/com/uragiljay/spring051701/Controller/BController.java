package com.uragiljay.spring051701.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uragiljay.spring051701.Command.BCommand;
import com.uragiljay.spring051701.Command.BListCommand;
import com.uragiljay.spring051701.Command.BWriteCommand;

@Controller
public class BController {

	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.excute(model);
		
		return "list";
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		
		command = new BWriteCommand();
		command.excute(model);
		
		return "write";
	}
}
