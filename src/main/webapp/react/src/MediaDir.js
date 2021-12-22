import React from 'react';
import MediaItem from './MediaItem';
import Card from 'react-bootstrap/Card';
import Accordion from 'react-bootstrap/Accordion';

export default class MediaDir extends React.Component {

    render() {
        let mediaList = this.props.dir.mediaList.map((media) => 
            <MediaItem key={media.filePath} media={media} />
        );

        return (
            <Card className="bg-dark">
                <Accordion.Toggle className="text-light" as={Card.Header} 
                    variant="link" eventKey={this.props.dir.dirPath}
                    style={{cursor:'pointer'}}>
                    {this.props.dir.dirName}
                </Accordion.Toggle>
                <Accordion.Collapse eventKey={this.props.dir.dirPath}>
                    <Card.Body className="bg-secondary">{mediaList}</Card.Body>
                </Accordion.Collapse>
            </Card>
        );
    }

}