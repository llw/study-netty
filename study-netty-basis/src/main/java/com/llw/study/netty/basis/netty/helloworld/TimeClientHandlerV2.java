package com.llw.study.netty.basis.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandlerV2 extends ChannelInboundHandlerAdapter {
	
	private byte[] req;
	
	private int counter;

	public TimeClientHandlerV2() {
		super();
		 req =("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
		
		
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf firstMessage=null;
		for(int i=0;i<100;i++){
			firstMessage = Unpooled.buffer(req.length);
			firstMessage.writeBytes(req);
			ctx.writeAndFlush(firstMessage);
		}
		
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8");
		System.out.println("Now is:"+body+"; the counter is : "+ ++counter);
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

}
