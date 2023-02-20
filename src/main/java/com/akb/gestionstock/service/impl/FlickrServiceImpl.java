package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.service.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public class FlickrServiceImpl implements FlickrService {

    private Flickr flickr;

    @Autowired
    public FlickrServiceImpl(Flickr flickr){
        this.flickr = flickr;
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);

        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }
}
