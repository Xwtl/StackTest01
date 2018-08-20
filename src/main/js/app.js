const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {people: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/data/people'}).done(response => {
            this.setState({people: response.entity.content});
        });
    }

    render() {
        return ( <div>
                <PeopleList peopleList = {this.state.people} />
                <TestForm testForm />
            </div>

        )
    }
}

class PeopleList extends React.Component {
    render() {
        var peopleList = this.props.peopleList.map(person =>
            <Person key={person.userNum} person={person}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                </tr>
                {peopleList}
                </tbody>
            </table>
        )
    }
}

class Person extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.person.firstName}</td>
                <td>{this.props.person.lastName}</td>
                <td>{this.props.person.email}</td>
            </tr>
        )
    }
}

class TestForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        var submittedName = this.state.value;
        client({method: 'GET', path: '/data/test/projects/' + submittedName}).done(response => {
            console.log(response)

            alert('First name: ' + submittedName + "\nProject name: " +
                response.entity[0].projectName
            );

        });
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Name:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

ReactDOM.render(
    <App />, document.getElementById('react')
);