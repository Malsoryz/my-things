program tarifpln;

uses
  Vcl.Forms,
  pln in 'pln.pas' {flatihan11};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(Tflatihan11, flatihan11);
  Application.Run;
end.
