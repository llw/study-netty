package com.llw.study.netty.basis.netty.decoder;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	
	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body = (String)msg;
		System.out.println("The time server receive order: "+body+"; the counter is : "+ ++counter);
		String 	currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
				new Date(System.currentTimeMillis()).toString():"BAD ORDER";
		ByteBuf resp = Unpooled.copiedBuffer((currentTime+System.getProperty("line.separator")).getBytes());
		ctx.write(resp);
		super.channelRead(ctx, msg);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
		super.exceptionCaught(ctx, cause);
	}
	
	public static void main(String args[]){
		System.out.println(("a"+System.getProperty("line.separator")+"b").length());
	}

}
