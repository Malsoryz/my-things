program projectlatihan10;

uses
  Vcl.Forms,
  ubahnumtotext in 'ubahnumtotext.pas' {flatihan10};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(Tflatihan10, flatihan10);
  Application.Run;
end.
