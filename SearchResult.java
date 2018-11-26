class SearchResult implements Comparable<SearchResult>{
  String text;
  int time;
  int qo;

  SearchResult(String text, int time, int qo){
    this.text = text;
    this.time = time;
    this.qo = qo;
  }

  public String getText(){
    return text;
  }

  public int getTime(){
    return time;
  }

  public int getQO(){
    return qo;
  }

  public Boolean equal(SearchResult b){
    if(this.text.equals(b.getText())) return true;
    else return false;
  }

  public int compareTo(SearchResult otherObject){
    if(time == otherObject.getTime()){
      if(qo > otherObject.getQO()) return (1);
      else if(qo < otherObject.getQO()) return (-1);
      else return 0;
    }

    if(time > otherObject.getTime()) return (1);
    else return (-1);
  }
}
