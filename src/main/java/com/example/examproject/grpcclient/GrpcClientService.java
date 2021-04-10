package com.example.examproject.grpcclient;

import com.example.MessageServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    public void sendMessageToServer(String message) {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        MessageServerGrpc.MessageServerBlockingStub stub = MessageServerGrpc.newBlockingStub(managedChannel);

        com.example.Service.GetMessageRequest messageRequest = com.example.Service.GetMessageRequest.newBuilder()
                .setMessage(message)
                .build();

        stub.getMessage(messageRequest);


        managedChannel.shutdownNow();
    }
}
