import React from 'react';
import MediaDir from './MediaDir';
import Accordion from 'react-bootstrap/Accordion';

export default class MediaList extends React.Component {

    render() {
        let dirList = {};
        let dirPathList = [];

        console.log('this.props.mediaList: ' + JSON.stringify(this.props.mediaList));

        this.props.mediaList
            .filter(media => media.directory)
            .forEach(media => {
                dirList[media.filePath] = {dirName: media.fileName, dirPath: media.filePath, mediaList: []};
                dirPathList.push(media.filePath);
        });

        this.props.mediaList
            .filter(media => !media.directory)
            .forEach(media => {
                let limitDirPath = media.filePath.lastIndexOf(media.fileName) - 1;
                let dirPath = media.filePath.substr(0, limitDirPath);
                console.log('dirPath: ' + dirPath);
                dirList[dirPath].mediaList.push(media);
        });

        dirPathList.forEach(dirPath => {
            let dir = dirList[dirPath];
            if (!dir.mediaList || dir.mediaList.length === 0) {
                delete dirList[dirPath];
                dirPathList = dirPathList.filter(dp => dp !== dirPath);
            }
        });

        console.log(JSON.stringify(dirList));
        console.log(JSON.stringify(dirPathList));

        let mediaDirList = <p className="text-light text-center">Nenhum arquivo encontrado.</p>;

        if (dirPathList && dirPathList.length > 0) {
            mediaDirList = dirPathList.map((dir) => 
                <MediaDir key={dir} dir={dirList[dir]} dataParentId="accordionMediaList" />
            );
        }

        return (
            <Accordion id="accordionMediaList">
                {mediaDirList}
            </Accordion>
        );
    }

}