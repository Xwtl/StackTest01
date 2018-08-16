const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {people: {}};
    }

    componentDidMount() {
        client({method: 'GET', path: '/data/people'}).done(response => {
            this.setState({people: response.entity});
        });
    }

    render() {
        return (
            <p>REACT</p>
        )
    }
}

ReactDOM.render(
    <App />, document.getElementById('react')
)